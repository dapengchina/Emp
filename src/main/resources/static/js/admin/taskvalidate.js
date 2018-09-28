$(function () {
	//添加时
    $('#addTaskForm').bootstrapValidator({
　　　　message: 'This value is not valid',
      　     feedbackIcons: {
        　　　　　　　　valid: 'glyphicon glyphicon-ok',
        　　　　　　　　invalid: 'glyphicon glyphicon-remove',
        　　　　　　　　validating: 'glyphicon glyphicon-refresh'
      　　　　　　　　  },
      fields: {
    	taskNameAdd: {
          validators: {
            notEmpty: {
              message: 'Please Input TaskName'
            }
          }
        },
        course: {
            validators: {
              notEmpty: {
                message: 'Please Select Course'
              }
            }
        },
        startDateAdd: {
          validators: {
            notEmpty: {
              message: 'Please Input StartDate'
            }
          }
        },
        endDateAdd: {
            validators: {
              notEmpty: {
                message: 'Please Input EndDate'
              }
            }
        }
      }
    });
    
    //编辑时
    $('#editTaskForm').bootstrapValidator({
    　　　　message: 'This value is not valid',
          　     feedbackIcons: {
            　　　　　　　　valid: 'glyphicon glyphicon-ok',
            　　　　　　　　invalid: 'glyphicon glyphicon-remove',
            　　　　　　　　validating: 'glyphicon glyphicon-refresh'
          　　　　　　　　  },
          fields: {
        	taskNameEdit: {
              validators: {
                notEmpty: {
                  message: 'Please Input TaskName'
                }
              }
            },
            startDateEdit: {
              validators: {
                notEmpty: {
                  message: 'Please Input StartDate'
                }
              }
            },
            endDateEdit: {
                validators: {
                  notEmpty: {
                    message: 'Please Input EndDate'
                  }
                }
            }
          }
     });
});
