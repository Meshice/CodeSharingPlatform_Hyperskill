<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" href="css/get_code.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>
        hljs.initHighlighting.called = false;
        hljs.initHighlighting();
    </script>
<#--    <style><#include "css/get_code.css"></style>-->
</head>
<body>
    <#list codes as code>
        <span id="load_date" class="date">${code.getDate()}</span>
        <div class="code">
        <pre id="code_snippet"><code>${code.getCode()}</code></pre>
        </div>
    </#list>
</body>
</html>