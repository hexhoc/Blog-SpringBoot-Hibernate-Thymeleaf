$(function () {
    $("#jqGrid").jqGrid({
        url:'/admin/articles/list',
        datatype: "json",
        colModel: [
            {label:'id', name:'id', index:'id', width: 50, key: true, hidden: true},
            {label:'Title', name:'title', index:'title', width: 140},
            {label:'Preview image', name:'coverImage', index:'coverImage', width: 120, formatter: coverImageFormatter},
            {label:'Views', name:'views', index:'views', width: 60},
            {label:'Status', name:'status', index:'status', width: 60, formatter: statusFormatter},
            {label:'blog category', name:'categoryName', index:'categoryName', width: 60},
            {label:'Add time', name:'createTime', index:'createTime', width: 90}
        ],
        height: 700,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI:'Bootstrap',
        loadtext:'Information reading...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPages",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //Hide the scroll bar at the bottom of the grid
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function coverImageFormatter(cellvalue) {
        return "<img src='" + cellvalue + "'height=\"120\" width=\"160\" alt='coverImage'/>";
    }

    function statusFormatter(cellvalue) {
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 50%;\">Draft</button>";
        }
        else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 50%;\">Publish</button>";
        }
    }

});

/**
 * searching feature
 */
function search() {
    //Title keywords
    var keyword = $('#keyword').val();
    if (!validLength(keyword, 20)) {
        swal("The length of the search field is too large!", {
            icon: "error",
        });
        return false;
    }
    //Data encapsulation
    var searchData = {keyword: keyword};
    //Pass in query condition parameters
    $("#jqGrid").jqGrid("setGridParam", {postData: searchData});
    //Click the search button to start from the first page by default
    $("#jqGrid").jqGrid("setGridParam", {page: 1});
    //Submit post and refresh the form
    $("#jqGrid").jqGrid("setGridParam", {url:'/admin/articles/list'}).trigger("reloadGrid");
}

/**
 * jqGrid reload
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function addBlog() {
    window.location.href = "/admin/articles/edit";
}

function editBlog() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/articles/edit/" + id;
}

function deleteBlog() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "Confirmation popup",
        text: "Are you sure you want to delete the data?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/articles/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("Delete successfully", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}