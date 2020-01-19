# alias
Syntax:	alias path;  
Default: —  
Context: location

alias 与 root 一样，是用来返回静态文件的，不会改变原始 URL，如果 location 里面又有 alias，又有 proxy_pass 或者 echo 等，则 alias 会失效。  
示例：
```
1） 建立目录：/usr/local/nginx/html/new-root
2） 在上面新建的目录下，新建文件：hello.txt, 文件内容： hello world
3)  在 $NGINX_HOME/conf/ 目录下，新建配置文件 alias.conf，内容如下：
worker_processes  1;

events {
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    server {
        listen       80;
        server_name  localhost;

        location /a/ {
            alias /usr/local/nginx/html/new-root/;
        }
    }
}


4)  启动 nginx: nginx -c $NGINX_HOME/conf/alias.conf

5)  浏览器输入 http://localhost/a/hello.txt，页面显示： hello world
``` 

结论：  
URL(http://localhost/a/[xxx]/a.txt) ==> File(/usr/local/nginx/html/new-root/[xxx]/a.txt) 