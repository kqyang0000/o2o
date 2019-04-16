$(function () {
    var loading = false;
    // 允许分页返回的最大条数，超过此数则禁止访问后台
    var maxItems = 999;
    // 默认一页返回的商品数
    var pageSize = 3;
    // 列出商品列表的URL
    var listUrl = "/o2o/frontend/listproductsbyshop";
    // 默认的页码
    var pageNum = 1;
    // 从地址栏里获取shopId
    var shopId = getQueryString("shopId");
    var productCategoryId = "";
    var productName = "";
    // 获取本店铺信息以及商品类别信息列表的URL
    var searchDivUrl = "/o2o/frontend/listshopdetailpageinfo?shopId=" + shopId;
    // 渲染出店铺基本信息以及商品类别列表以供搜索
    getSearchDivData();
    // 预先加载10条商品信息
    addItems(pageSize, pageNum);

    // 给兑换礼品的a标签赋值兑换礼品的URL，2.0讲解
    // $("#exchangelist").attr("href", "/o2o/frontend/awardlist?shopId=" + shopId);

    /**
     * 获取本店铺信息以及商品类别信息列表
     */
    function getSearchDivData() {
        var url = searchDivUrl;
        $.getJSON(url, function (data) {
            if (data.success) {
                var shop = data.shop;
                $("#shop-cover-pic").attr("src", shop.shopImg);
                $("#shop-update-time").html(new Date(shop.lastEditTime).Format("yyyy-MM-dd") + "更新");
                $("#shop-name").html(shop.shopName);
                $("#shop-desc").html(shop.shopDesc);
                $("#shop-addr").html(shop.shopAddr);
                $("#shop-phone").html(shop.phone);
                // 获取后台返回的该店铺的商品列表
                var productCategoryList = data.productCategoryList;
                var html = "";
                productCategoryList.map(function (item, index) {
                    html += "<a href='#' class='button' data-product-search-id='" + item.productCategoryId
                        + "'>" + item.productCategoryName + "</a>"
                });
                $("#shopdetail-button-div").html(html);
            }
        });
    }

    /**
     * 获取分页展示的商品列表信息
     * @param pageSize
     * @param pageIndex
     */
    function addItems(pageSize, pageIndex) {
        // 拼接出查询的URL，赋空值默认就去掉这个条件的限制，有值就代表按这个条件查询
        var url = listUrl + "?" + "pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&productCategoryId="
            + productCategoryId + "&productName=" + productName + "&shopId=" + shopId;
        // 设定加载符，若还在后台取数据则不能再次访问后台，避免多次重复加载
        loading = true;
        // 访问后台获取响应查询条件下的带商品列表
        $.getJSON(url, function (data) {
            if (data.success) {
                // 获取当前查询条件下商品的总数
                maxItems = data.count;
                var html = "";
                // 遍历商品列表，并拼接出卡片集合
                data.productList.map(function (item, index) {
                    html += "<div class='card' data-product-id='" + item.productId + "'><div class='card-header'>" + item.productName + "</div>"
                        + "<div class='card-content'><div class='list-block media-list'><ul><li class='item-content'><div class='item-media'>"
                        + "<img src='" + item.imgAddr + "' width='44'/></div><div class='item-inner'><div class='item-subtitle'>"
                        + item.productDesc + "</div></div></li></ul></div></div><div class='card-footer'><p class='color-gray'>"
                        + new Date(item.lastEditTime).Format('yyyy-MM-dd') + "更新</p><span>点击查看</span></div></div>";
                });
                $(".list-div").append(html);
                // 获取目前为止已显示的卡片总数，包含之前已经加载的
                var total = $(".list-div .card").length;
                // 若总数达到跟查询条件列出来的总数一致，则停止后台的加载
                if (total >= maxItems) {
                    $(".infinite-scroll-preloader").hide();
                } else {
                    $(".infinite-scroll-preloader").show();
                }
                // 否则页码加1，继续load出新的店铺
                pageNum += 1;
                // 加载结束，可以再次加载了
                loading = false;
                // 刷新页面，显示新加载的店铺
                $.refreshScroller();
            }
        });
    }

    // 下滑屏幕自动进行分页搜索
    $(document).on("infinite", ".infinite-scroll-bottom", function () {
        if (loading) {
            return;
        }
        addItems(pageSize, pageNum);
    });

    // 选择新的店铺类别之后，重置页码，清空原先的店铺列表，按照新的类别去查询
    $("#shopdetail-button-div").on("click", ".button", function (e) {
        productCategoryId = e.target.dataset.productSearchId;
        if (productCategoryId) {
            // 若之前选定了别的category，则移除其选定效果，改成选定新的
            if ($(e.target).hasClass("button-fill")) {
                $(e.target).removeClass("button-fill");
                productCategoryId = "";
            } else {
                $(e.target).addClass("button-fill").siblings().removeClass("button-fill");
            }
            // 由于查询条件改变，清空店铺列表再进行查询
            $(".list-div").empty();
            // 重置页码
            pageNum = 1;
            addItems(pageSize, pageNum);
        }
    });

    $(".list-div").on("click", ".card", function (e) {
        var productId = e.currentTarget.dataset.productId;
        window.location.href = "/o2o/frontend/productdetail?productId=" + productId;
    });

    // 需要查询的店铺名字发生变化后，重置页码，清空原先的店铺列表，按照新的名字重新查询
    $("#search").on("change", function (e) {
        productName = e.target.value;
        $(".list-div").empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    // 点击后打开右侧栏
    $("#me").click(function () {
        $.openPanel("#panel-right-demo");
    });

    // 舒适化页面
    $.init();
});