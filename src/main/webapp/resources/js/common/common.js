/**
 * 开始的时候img没有传进来，这个方法实现点击更换验证码
 * */
function changeVerifyCode(img) {
    img.src="../Kaptcha?" + Math.floor(Math.random() * 100);
}