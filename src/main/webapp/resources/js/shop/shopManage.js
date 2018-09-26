$(function () {
    var shopId = getQueryString('shopId');
    var shopInfoUrl = '/shopadmin/getshopmanagementinfo?shopId=' + shopId;
    //alert(shopInfoUrl);
    $.getJSON(shopInfoUrl, function (data) {
        if (data.redirect) {
            window.location.href = data.url;
        } else {
            if (data.shopId != undefined && data.shopId != null) {
                shopId = data.shopId;
            }
            $('#shopInfo').attr('href', '/shopadmin/shopoperation?shopId='+shopId);
        }
    });

    //该方法放在common.js中不起作用
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURIComponent(r[2]);
        }
        return '';
    }
});