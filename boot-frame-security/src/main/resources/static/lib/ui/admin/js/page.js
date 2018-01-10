PageTable = {
    responseHandler: function (res) {
        return {
            "total": res.total,  //总页数
            "rows": res.records   //数据
        };
    },
    queryParams: function (params) {
        var current = (params.offset == 0 ? 1 : (params.offset / params.limit + 1));
        return $.extend(params, {
            "current": current,
            "size": params.limit,
            "orderByField": params.sort,
            "asc": (params.order == "desc" ? false : true)
        });
    }
};
