# root
Syntax:	root path;  
Default: root html  
Context: http, server, location, if in location

设置请求的静态文件的根目录  
示例：
```
配置：
location /static/ {
    root /usr/local/nginx/html/;
}

请求URL：http://localhost/static/hello.html   ==> /usr/local/nginx/html/static/hello.html
```