$(function () {
    var listUrl = "/o2o/shopadmin/getproductcategorylist";
    var addUrl = "/o2o/shopadmin/addproductcategory";
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
            html += "<div class='row row-product-category'><div class='col-40'>" + item.productCategoryName + "</div>" +
                "<div class='col-40'>" + item.priority + "</div><div class='col-20'>" +
                removeProductCategory(item.productCategoryId) + "</div></div>";
        });
        $(".product-category-wrap").html(html);
    }

    function removeProductCategory(id) {
        return "<a href='#' class='button'>删除</a>";
    }
});