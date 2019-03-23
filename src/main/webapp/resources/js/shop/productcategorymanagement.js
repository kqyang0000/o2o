$(function () {
    var listUrl = "/o2o/shopadmin/getproductcategorylist";
    var addUrl = "/o2o/shopadmin/addproductcategorys";
    var deleteUrl = "/o2o/shopadmin/removeproductcategory";
    getList();

    function getList() {
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                handleList(data.data);
            }
        });
    }

    function handleList(data) {
        var html = "";
        data.map(function (item, index) {
            html += "<div class='row row-product-category now'><div class='col-40'>" + item.productCategoryName + "</div>" +
                "<div class='col-40'>" + item.priority + "</div><div class='col-20'>" +
                removeProductCategory(item.productCategoryId) + "</div></div>";
        });
        $(".product-category-wrap").html(html);
    }

    function removeProductCategory(id) {
        return "<a class='button delete' data-id='" + (undefined == id ? "" : id) + "'>删除</a>";
    }

    $("#new").click(function () {
        var tempHtml = "<div class='row row-product-category temp'><div class='col-40'>" +
            "<input class='product-category-input product-category' type='text' placeholder='类别'/></div>" +
            "<div class='col-40'><input class='product-category-input priority' type='number' placeholder='优先级'/>" +
            "</div><div class='col-20'>" + removeProductCategory() + "</div></div>";
        $(".product-category-wrap").append(tempHtml);
    });

    $("#submit").click(function () {
        var tempArr = $(".temp");
        var productCategoryList = [];
        tempArr.map(function (index, item) {
            var tempObj = {};
            tempObj.productCategoryName = $(item).find(".product-category").val();
            tempObj.priority = $(item).find(".priority").val();
            if (tempObj.productCategoryName && tempObj.priority) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url: addUrl,
            type: "post",
            data: JSON.stringify(productCategoryList),
            contentType: "application/json",
            success: function (data) {
                if (data.success) {
                    getList();
                    $.toast("提交成功!");
                } else {
                    $.toast("提交失败!");
                }
            }
        });
    });

    $(".product-category-wrap").on('click', '.delete', function () {
        var productCategoryId = $(this).attr("data-id");
        if ("" == productCategoryId) {
            $(this).parent().parent().remove();
        } else {
            $.confirm("确定吗?", function () {
                $.ajax({
                    url: deleteUrl,
                    type: "post",
                    data: {productCategoryId: productCategoryId},
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            $.toast("删除成功!");
                            getList();
                        } else {
                            $.toast("删除失败!");
                        }
                    }
                });
            });
        }
    });
});