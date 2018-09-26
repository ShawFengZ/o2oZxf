$(function () {
    getlist();
    function getlist(e) {
        $.ajax({
            url: "/shopadmin/getshoplist",
            type:"get",
            dataType:"json",
            success:function (data) {
                //alert(JSON.stringify(data));
                handleList(data.shopList);
                handleUser(data.user);
            }
        });
    }

    function handleUser(data) {
        $('#user-name').text(data.name);
    }
    function handleList(data) {
        var html = '';
        data.map(function (item, index) {
            html += '<div class="row row-shop"><div class="col-40">'
                 + item.shopName + '</div><div class="col-40">'
                 + shopStatus(item.enableStatus) + '</div><div class="col-20"> '
                 + goShop(item.enableStatus, item.shopId) + '</div></div>'
        });
        $('.shop-wrap').html(html);
    }

    //根据后端返回的status显示文字
    function shopStatus(status) {
        if (status == 0)
            return '审核中';
        else if (status == -1)
            return '店铺非法';
        else if (status == 1)
            return '审核通过'
    }

    //到店铺管理页面
    function goShop(status, id) {
        if (status == 1)
            return '<a href="/shopadmin/shopmanage?shopId='+id+'">进入</a>';
        else
            return '';
    }
});