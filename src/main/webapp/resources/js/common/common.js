/**
 * 开始的时候img没有传进来，这个方法实现点击更换验证码
 * */
function changeVerifyCode(img) {
    img.src="../Kaptcha?" + Math.floor(Math.random() * 100);
}

/**
 * 从输入的url中获取shopId
 * */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}