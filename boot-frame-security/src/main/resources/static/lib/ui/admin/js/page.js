PageTable = {
    responseHandler: function(res) {
        return {
            "total": res.total,  //总页数
            "rows": res.records   //数据
        };
    },
    queryParams: function(params) {
        console.info(params);
        return {
            "page.offset": params.offset,
            "page.offset": params.offset
        };
    }
};