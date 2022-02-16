package platform.code_service;

import javax.persistence.*;

@Entity
@Table(name = "id_uuid")
public class UUIDEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String uuid;

    @OneToOne(mappedBy = "uuid",cascade = CascadeType.ALL)
    private Code code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
