$(function () {
    // 获取此店铺下的商品列表的url
    var listUrl = "/o2o/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999";
    // 商品下架url
    var statusUrl = "/o2o/shopadmin/modifyproduct";
    getList();

    /**
     * 获取此店铺下的商品列表
     */
    function getList() {
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var productList = data.productList;
                var tempHtml = "";
                // 遍历每条商品信息，拼接成一行，列信息包括：商品名称 优先级 上/下架（包含productId） 编辑按钮（包含productId） 预览（包含productId）
                productList.map(function (item, index) {
                    var textOp = "下架";
                    var contrayStatus = 0;
                    if (item.enableStatus == 0) {
                        textOp = "上架";
                        contrayStatus = 1;
                    } else {
                        contrayStatus = 0;
                    }
                    // 拼接每件商品的行信息
                    tempHtml += "<div class='row row-product'><div class='col-33'>" + item.productName
                        + "</div><div class='col-20'>" + item.point
                        + "</div><div class='col-40'><a href='#' class='edit' data-id='" + item.productId
                        + "' data-status='" + item.enableStatus + "'>编辑</a><a href='#' class='status' data-id='" + item.productId
                        + "' data-status='" + contrayStatus + "'>" + textOp + "</a><a href='#' class='preview' data-id='" + item.productId
                        + "' data-status='" + item.enableStatus + "'>预览</a></div></div>";
                    $(".product-wrap").html(tempHtml);
                });
            }
        });
    }

    //将class为product-wrap里面的a标签绑定上点击事件
    $(".product-wrap").on('click', 'a', function (e) {
        var target = $(e.currentTarget);
        if (target.hasClass('edit')) {
            window.location.href = "/o2o/shopadmin/productoperation?productId=" + e.currentTarget.dataset.id;
        } else if (target.hasClass('status')) {
            changeItemStatus(e.currentTarget.dataset.id, e.currentTarget.dataset.status);
        } else if (target.hasClass('preview')) {
            window.location.href = "/o2o/frontend/productdetail?productId=" + e.currentTarget.dataset.id;
        }
    });

    function changeItemStatus(id, enableStatus) {
        // 定义product json对象并添加productId以及状态(上/下架)
        var product = {};
        product.productId = id;
        product.enableStatus = enableStatus;
        $.confirm('确定么?', function () {
            $.ajax({
                url: statusUrl,
                data: {"reqStr": JSON.stringify(product), "statusChange": true},
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $.toast("操作成功");
                        getList();
                    } else {
                        $.toast("操作失败");
                    }
                }
            });
        })
    }


});