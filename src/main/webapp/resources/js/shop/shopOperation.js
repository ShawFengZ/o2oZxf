$(function () {
    var initUrl = '/shopadmin/getshopinitinfo';
    var registerShopUrl='/shopadmin/registershop';
    //alert(initUrl);
    getShopInitInfo();
    //获取区域信息和店铺信息到下拉列表中
    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                //alert(JSON.stringify(data));
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                    + item.shopCategoryName + '</option>';
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                    + item.areaName + '</option>';
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
    }
    //提交方法
    $('#submit').click(function () {
        var shop={};
        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.shopCategoryId =  $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id');
        shop.areaId = $('#area').find('option').not(function () {
            return !this.selected;
        }).data('id');
        /*shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };*/
        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        //传入验证码
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast("请输入验证码");
            return;
        }
        formData.append('verifyCodeActual', verifyCodeActual)
        $.ajax({
            url: registerShopUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success:function (data) {
                if (data.success) {
                    $.toast("提交成功！");
                } else {
                    $.toast("提交失败！" + data.errMsg);
                }
                //无论提交成功与否都更换一下验证码
                $('#captcha_img').click();
            }
        });
    });

    //这里还需要一个方法是验证表单的输入

});