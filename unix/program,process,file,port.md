# program,process,file,port

program -> process：pidof <program>
process -> program: ps

file -> process: fuser <file/dir>
process -> file: lsof

process -> port: netstat -anp (Linux)
port -> process: 
  lsof -i:<port>
  netstat -anv(Mac) 、 netstat -anp(Linux)