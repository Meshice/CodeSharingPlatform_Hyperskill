package platform.code_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.code_dao.CodeRepository;
import platform.code_dao.UUIDRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CodeService {

    @Autowired
    CodeRepository codeRepository;

    @Autowired
    UUIDRepository uuidRepository;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public CodeService() {
    }

    public String addCode(Code code) {
        code.setDate(LocalDateTime.now().format(dateTimeFormatter));
        UUIDEntity uuidEntity = new UUIDEntity();
        uuidEntity.setUuid(UUID.randomUUID().toString());
        uuidEntity.setCode(code);
        code.setUuid(uuidEntity);
        if (code.getTime() > 0) {
            code.setTimeIsRequired(true);
            code.setHidden(true);
        }
        if (code.getViews() > 0) {
            code.setViewIsRequired(true);
            code.setHidden(true);
        }
        codeRepository.save(code);
        return uuidEntity.getUuid();
    }

    public List<Code> getLatestCodes() {
        List<Code> codes = (List<Code>) codeRepository.findAll();
        return codes.stream().sorted((o1,o2) -> Integer.compare(o2.getId(), o1.getId())).filter(code -> !code.isHidden()).limit(10).collect(Collectors.toList());
    }

    public Code getCodeById(String uuid) {
        Optional<UUIDEntity> uuidEntity = uuidRepository.findByUuid(uuid);
        if (uuidEntity.isPresent()) {
            Code code = uuidEntity.get().getCode();
            decreaseCodeLimit(code);
            if (checkCodeExpire(code)) {
                deleteCode(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
            codeRepository.save(code);
            return code;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bad ID");
        }
    }

    private void deleteCode(Code code) {
        codeRepository.delete(code);
    }

    private boolean checkCodeExpire(Code code) {
        return (code.getViews() < 0 && code.isViewIsRequired()) || (code.getTime() <= 0 && code.isTimeIsRequired());
    }

    private void decreaseCodeLimit(Code code) {
        LocalDateTime createTime = LocalDateTime.parse(code.getDate(), dateTimeFormatter);
        LocalDateTime nowTime = LocalDateTime.now();
        if (code.isViewIsRequired()) {
            code.setViews(code.getViews() - 1);
        }
        long secondSinceCreation = ChronoUnit.SECONDS.between(createTime, nowTime);
        if (code.isTimeIsRequired()) {
            code.setTime(code.getTime() - secondSinceCreation);
        }
    }


}
