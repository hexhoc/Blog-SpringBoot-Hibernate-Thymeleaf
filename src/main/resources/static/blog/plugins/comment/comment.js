$('#commentSubmit').click(function () {
    var blogId = $('#blogId').val();
    var verifyCode = $('#verifyCode').val();
    var commentator = $('#commentator').val();
    var email = $('#email').val();
    var websiteUrl = $('#websiteUrl').val();
    var commentBody = $('#commentBody').val();
    if (isNull(blogId)) {
        swal("Parameter exception", {
            icon: "warning",
        });
        return;
    }
    if (isNull(commentator)) {
        swal("Please enter your name", {
            icon: "warning",
        });
        return;
    }
    if (isNull(email)) {
        swal("Please enter your email", {
            icon: "warning",
        });
        return;
    }
    if (isNull(verifyCode)) {
        swal("Please enter the verification code", {
            icon: "warning",
        });
        return;
    }
    if (!validCN_ENString2_100(commentator)) {
        swal("Please enter a name that meets the specification (do not enter special characters)", {
            icon: "warning",
        });
        return;
    }
    if (!validCN_ENString2_100(commentBody)) {
        swal("Please enter the content of the comment that meets the specification (do not enter special characters)", {
            icon: "warning",
        });
        return;
    }
    var data = {
        "blogId": blogId, "verifyCode": verifyCode, "commentator": commentator,
        "email": email, "websiteUrl": websiteUrl, "commentBody": commentBody
    };
    console.log(data);
    $.ajax({
        type:'POST',//Method type
        url:'/blog/comment',
        data: data,
        success: function (result) {
            if (result.resultCode == 200) {
                swal("Comment submitted successfully, please wait for the blogger to review", {
                    icon: "success",
                });
                $('#commentBody').val('');
                $('#verifyCode').val('');
            }
            else {
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
});