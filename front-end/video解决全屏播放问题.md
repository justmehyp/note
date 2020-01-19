# video解决全屏播放问题

ios的微信浏览器为chrome内核

在video标签添加属性

webkit-playsinline="true" x-webkit-airplay="true" playsinline="true"

 

安卓的为x5内核

在video标签添加属性

x5-playsinline

切记 一定要将x5-video-player-type="h5"属性去除，他会坑死你的。切记切记！

 若两端全部支持，给出项目一个示例代码

<video controls="controls" preload="auto" poster="" x5-video-player-fullscreen="true" 
    webkit-playsinline="true" x-webkit-airplay="true" playsinline="true" 
    x5-playsinline 
    style="width: 300px;height:200px;">  

 <source src="" type="video/mp4" />

</video>
