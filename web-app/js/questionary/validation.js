   $("#questionaryForm").validate({
      errorPlacement: function(error, element) {
        error.addClass("help-inline"));
      },
      submitHandler: function(form) {
        form.submit();
      }
    });