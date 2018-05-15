$(function () {
    $("#addAdminForm").bootstrapValidator({
　　　　message: 'This value is not valid',
      　     feedbackIcons: {
        　　　　　　　　valid: 'glyphicon glyphicon-ok',
        　　　　　　　　invalid: 'glyphicon glyphicon-remove',
        　　　　　　　　validating: 'glyphicon glyphicon-refresh'
      　　　　　　　　  },
      fields: {
    	 AdminName: {
    		 validators: {
    			 notEmpty: {
    				 message: 'The username can not be empty'
    			 },
	            stringLength: {
	                min: 4,  
	                max: 18,  
	                message: 'The username length should be between 4 and 18.'
	            },  
	            regexp: {
	                regexp: /^[a-zA-Z0-9_]+$/, 
	                message: 'The username can only contain uppercase, lowercase, digits, and underlines.'  
	            },
	            remote: {
	            	threshold: 4,
		        	url: "/Emp/admin/adminuser/verifieName",
		        	message: 'The username already existed',
		        	type:'post',
		            data:{
		            	AdminName:function(){
		            		return $('#AdminName').val();
		            	}
		            }
	            }
    		}
        },
        
        AdminPwd: {
        	 message:'Invalid password',
             validators: {
                 notEmpty: {
                     message: 'The password can not be empty'
                 },
                 stringLength: {
                     min: 4,
                     max: 30,
                     message: 'The password length should be between 4 and 30'
                 },
                 regexp: {
                     regexp: /^[a-zA-Z0-9_\.]+$/,
                     message: 'The password can only consist of alphabetical, number, dot and underscore'
                 }
             }
        },
        AdminConPwd: {
        	message: 'Invalid password',
            validators: {
                notEmpty: {
                    message: 'confirmation password can not be empty'
                },
                identical: {
                    field: 'AdminPwd',
                    message: 'The password and the confirmation password are not consistent'
                }
            }
        }
      }
    });
});
