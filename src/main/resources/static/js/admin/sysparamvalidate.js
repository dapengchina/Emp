$(function () {
    $('#addAdminForm').bootstrapValidator({
　　　　message: 'This value is not valid',
    group: '.rowGroup',
      　     feedbackIcons: {
        　　　　　　　　valid: 'glyphicon glyphicon-ok',
        　　　　　　　　invalid: 'glyphicon glyphicon-remove',
        　　　　　　　　validating: 'glyphicon glyphicon-refresh'
      　　　　　　　　  },
      fields: {
    	parentName: {
          validators: {
            notEmpty: {
              message: 'Please Input Name'
            },
            regexp: {
                regexp: /^[a-zA-Z0-9_]+$/, 
                message: 'The Name can only contain uppercase, lowercase, digits, and underlines.'  
            }
          }
        },
        childName: {
          validators: {
            notEmpty: {
              message: 'Please Input Name'
            },
            regexp: {
                regexp: /^[a-zA-Z0-9_]+$/, 
                message: 'The Name can only contain uppercase, lowercase, digits, and underlines.'  
            }
          }
        },
        childValue: {
			validators: {
				notEmpty: {
					message: 'Please Input value'
				}, 
				regexp: {
					regexp: /^[0-9_]+$/, 
					message: 'Only allowed numbers'  
				}
			}
        }
      }
    });
});
