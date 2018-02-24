PageTable = {
    responseHandler: function (res) {
        return {
            "total": res.total,  //总页数
            "rows": res.records   //数据
        };
    },
    queryParams: function (params) {
        var searchObject = {};
        $(".search").each(function (index, domEle) {
            var $domEle = $(domEle);
            if ($domEle.val() != null && $.trim($domEle.val()) != "") {
                searchObject[$domEle.attr("name")] = $domEle.val();
            }
        });
        if (params.offset != null) {
            var current = (params.offset == 0 ? 1 : (params.offset / params.limit + 1));
            return $.extend(params, {
                "current": current,
                "size": params.limit,
                "orderByField": params.sort,
                "asc": (params.order == "desc" ? false : true)
            }, searchObject);
        } else {
            return $.extend(params, {
                "current": 1,
                "size": 10000,
                "orderByField": params.sort,
                "asc": (params.order == "desc" ? false : true)
            }, searchObject);
        }
    }
};
