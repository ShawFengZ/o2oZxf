$(function () {
    var shopId = getQueryString('shopId');
    var isEdit = shopId?true:false;
    var initUrl = '/shopadmin/getshopinitinfo';
    var registerShopUrl='/shopadmin/registershop';
    var shopInfoUrl='/shopadmin/getshopbyid?shopId='+shopId;
    var editShopUrl='/shopadmin/modifyshop';
    if (!isEdit) {
        getShopInitInfo();
    } else {
        getShopInfo();
    }
    //alert(initUrl);

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
        if (!isEdit) {
            shop.shopId = shopId;
        }
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
        formData.append('verifyCodeActual', verifyCodeActual);
        alert(isEdit);
        $.ajax({
            url: isEdit?editShopUrl:registerShopUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: true,
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


    //传入shopId获取shop信息
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            //alert(JSON.stringify(data));
            //alert(JSON.stringify(data.shop.shopCategoryName));
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);
                var shopCategory = '<option data-id="'
                    + shop.shopCategoryId + '"selected>'
                    + shop.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                data.areaList.map(function () {
                   tempAreaHtml += '<option data-id"' + shop.areaId + '">'
                   +shop.areaName + '</option>';
                });
                $('#shop-category').html(shopCategory);
                $('#shop-category').attr('disabled', 'disabled');
                $('#area').html(tempAreaHtml);
                $('#area').attr('data-id', shop.areaId);
            }
        })
    }
});