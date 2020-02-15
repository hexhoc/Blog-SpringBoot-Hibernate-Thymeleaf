$(function () {
    $("#jqGrid").jqGrid({
        url:'/admin/tags/list',
        datatype: "json",
        colModel: [
            {label:'id', name:'id', index:'id', width: 50, key: true, hidden: true},
            {label:'tag name', name:'name', index:'name', width: 240},
            {label:'Add time', name:'createTime', index:'createTime', width: 120}
        ],
        height: 560,
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
});

/**
 * jqGrid reload
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam','page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function tagAdd() {
    var tagName = $("#tagName").val();
    if (!validCN_ENString2_18(tagName)) {
        swal("Improper label name", {
            icon: "error",
        });
    } else {
        var url ='/admin/tags/save?tagName=' + tagName;
        $.ajax({
            type:'POST',//Method type
            url: url,
            success: function (result) {
                if (result.resultCode == 200) {
                    $("#tagName").val('')
                    swal("Save successfully", {
                        icon: "success",
                    });
                    reload();
                }
                else {
                    $("#tagName").val('')
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("The operation failed", {
                    icon: "error",
                });
            }
        });
    }
}

function deleteTag() {
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
                    url: "/admin/tags/delete",
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