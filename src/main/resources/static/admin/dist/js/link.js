$(function () {
    $("#jqGrid").jqGrid({
        url:'/admin/links/list',
        datatype: "json",
        colModel: [
            {label:'id', name:'linkId', index:'linkId', width: 50, key: true, hidden: true},
            {label:'Website name', name:'linkName', index:'linkName', width: 100},
            {label:'Website link', name:'linkUrl', index:'linkUrl', width: 120},
            {label:'Website description', name:'linkDescription', index:'linkDescription', width: 120},
            {label:'Sort value', name:'linkRank', index:'linkRank', width: 30},
            {label:'Add time', name:'createTime', index:'createTime', width: 100}
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

function linkAdd() {
    reset();
    $('.modal-title').html('Friend Link Add');
    $('#linkModal').modal('show');
}

//Binding the save button on the modal
$('#saveButton').click(function () {
    var linkId = $("#linkId").val();
    var linkName = $("#linkName").val();
    var linkUrl = $("#linkUrl").val();
    var linkDescription = $("#linkDescription").val();
    var linkRank = $("#linkRank").val();
    if (!validCN_ENString2_18(linkName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("Please enter a name that conforms to the specification!");
        return;
    }
    if (!isURL(linkUrl)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("Please enter a compliant URL!");
        return;
    }
    if (!validCN_ENString2_100(linkDescription)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("Please enter a description that meets the specification!");
        return;
    }
    if (isNull(linkRank) || linkRank <0) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("Please enter a sorting value that meets the specification!");
        return;
    }
    var params = $("#linkForm").serialize();
    var url ='/admin/links/save';
    if (linkId != null && linkId> 0) {
        url ='/admin/links/update';
    }
    $.ajax({
        type:'POST',//Method type
        url: url,
        data: params,
        success: function (result) {
            if (result.resultCode == 200 && result.data) {
                $('#linkModal').modal('hide');
                swal("Save successfully", {
                    icon: "success",
                });
                reload();
            }
            else {
                $('#linkModal').modal('hide');
                swal("Save failed", {
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

});

function linkEdit() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    reset();
    //Request data
    $.get("/admin/links/info/" + id, function (r) {
        if (r.resultCode == 200 && r.data != null) {
            //Fill data to modal
            $("#linkName").val(r.data.linkName);
            $("#linkUrl").val(r.data.linkUrl);
            $("#linkDescription").val(r.data.linkDescription);
            $("#linkRank").val(r.data.linkRank);
            //Set the select selector to the selected state according to the original linkType value
            if (r.data.linkType == 1) {
                $("#linkType option:eq(1)").prop("selected",'selected');
            }
            if (r.data.linkType == 2) {
                $("#linkType option:eq(2)").prop("selected",'selected');
            }
        }
    });
    $('.modal-title').html('Friend Link Modification');
    $('#linkModal').modal('show');
    $("#linkId").val(id);
}

function deleteLink() {
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
                    url: "/admin/links/delete",
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

function reset() {
    $("#linkName").val('');
    $("#linkUrl").val('');
    $("#linkDescription").val('');
    $("#linkRank").val(0);
    $('#edit-error-msg').css("display", "none");
    $("#linkType option:first").prop("selected",'selected');
}