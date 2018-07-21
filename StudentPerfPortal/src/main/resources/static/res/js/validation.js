$( document ).ready(function() {

	var scoreregex = /^[0-9]+$/;

	$.validator.addMethod("validscore", function( value, element ) {
		return this.optional( element ) || scoreregex.test( value );
	});
	
	$.validator.addMethod("validscorerange", function( value, element ) {
		return this.optional( element ) || (value > 0 && value <= 20);
	});
	$("#single-form").validate({
		rules:
	    {
			s_age: {
				required: true,
				validscore: true
			},
			s_absences: {
				required: true,
				validscore: true
			},			
			s_grade1: {
				required: true,
				validscore: true,				
				validscorerange: true
			},
			s_grade2: {
				required: true,
				validscore: true,
				validscorerange: true
			},
	    },
	    messages:
	    {
	    	s_age: {
	    		required: "Please enter age",
				validscore: "Age must contain only digits"
	    	},
	    	s_absences: {
				required: "Please enter number of absences",
				validscore: "Number of absences must contain only digits"
			},
	    	s_grade1: {
	    		required: "Please Enter score",
	    		validscore: "Score must contain only digits",
	    		validscorerange: "Score should be between 0 and 20"
	    	},
	    	s_grade2: {
	    		required: "Please Enter score",
	    		validscore: "Score must contain only digits",
	    		validscorerange: "Score should be between 0 and 20"
	    	},
	    },
	    errorPlacement : function(error, element) {
	        $(element).closest('.form-group').find('.help-block').html(error.html());
	    },
	    highlight : function(element) {
	        $(element).closest('.form-group').find('.help-block').addClass('alert-sm').addClass('alert-danger');
	    },
	    unhighlight: function(element, errorClass, validClass) {
	        $(element).closest('.form-group').find('.help-block').removeClass('alert-sm').removeClass('alert-danger');
	        $(element).closest('.form-group').find('.help-block').html('');
	    },
	    submitHandler: function(form) {
	    	// Prevent the form from submitting via the browser.
	    	event.preventDefault();
	    	//form.submit();
	    	ajaxPost();
	    }
	});

	function ajaxPost(){   
		var s_age = $('#s_age').val();
		var s_famrel = $('#s_famrel').val();
		var s_absences = $('#s_absences').val();
		var s_grade1 = $('#s_grade1').val();
		var s_grade2 = $('#s_grade2').val();
		console.log('Values--->' +s_age + '  ****** ' + s_famrel + '*****' + s_absences + '******' + s_grade1 + '********' + s_grade2);
		var singlesubject =[];
		singlesubject.push({
			"age" : s_age,
			"famrel" : s_famrel,
			"absences" : s_absences,
			"grade1"  : s_grade1,
			"grade2"  : s_grade2
		});
		var inputObj = {};
		inputObj.cands=singlesubject;
		console.log('inputObj----->'+inputObj)
		$.ajax({
			url: "/singlesub",
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(inputObj),
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function (data) {
				console.log(data);
				console.log("Result: " + data[0][0] + "  Probability: "+data[0][1]);
				var s_result = data[0][0];
				$("label[for='passpercentage']").text(data[0][1]+'%');
				
				if (s_result == 'pass'){
					$("label[for='result']").removeClass("badge-danger").addClass("badge-success");					
					$("label[for='result']").text("Pass");
				}else{
					$("label[for='result']").removeClass("badge-success").addClass("badge-danger");					
					$("label[for='result']").text("Fail");
				}
				$("#resultddiv").removeClass("makeHidden").addClass("makeVisible"); 
			},
			error:function(er) {
				console.log("error: "+er);
			}
		});	

	}


	$("#resetbttn").click(function(){ 
		$("#s_age").val("");
		$("#s_famrel").val(-1);
		$("#s_absences").val("");
		$("#s_grade1").val("");
		$("#s_grade2").val("");
		$("#resultddiv").removeClass("makeVisible").addClass("makeHidden"); 
	});

	$("#multiple-form").validate({
	
	
		submitHandler: function(form) {
	    	// Prevent the form from submitting via the browser.
	    	event.preventDefault();
	    	//form.submit();
	    	ajaxPost2();
	    }
	});
	
	
	function ajaxPost2(){   
		var age = $('#age').val();
		var absences = $('#absences').val();
		var famrel = $('#famrel').val();
		var goout = $('#goout').val();
		var traveltime = $('#traveltime').val();
		var freetime = $('#freetime').val();
		var dalc = $('#dalc').val();
		
		
		var gradem1 = $('#gradem1').val();
		var gradem2 = $('#gradem2').val();
		var gradep1 = $('#gradep1').val();
		var gradep2 = $('#gradep2').val();
		console.log('Values--->' +age + '  ****** ' + absences + '*****' + famrel + '******' + goout + '********' + traveltime + '********'+ freetime + '********' + dalc);
		console.log('Values--->' + gradem1 + '*****' + gradem2 + '******' + gradep1 + '********' + gradep2);
		var multiplesubject =[];
		multiplesubject.push({
			"age" : age,
			"absences" : absences,
			"famrel" : famrel,
			"goout"  : goout,
			"traveltime" : traveltime,
			"freetime" : freetime, 
			"dalc"  : dalc,			
			"gradem1"  : gradem1,
			"gradem2"  : gradem2,
			"gradep1"  : gradep1,
			"gradep2"  : gradep2
		});
		var inputObj = {};
		inputObj.students=multiplesubject;
		console.log('inputObj----->'+inputObj)
		$.ajax({
			url: "/multiplesub",
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(inputObj),
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function (data) {
				console.log(data);
				console.log("Score1: " + data[0][0] + "  Score2: "+data[0][1]);	
				$("label[for='score_maths']").text(data[0][0]);
				$("label[for='score_physics']").text(data[0][1]);
				$('#exampleModal').modal('show');
				
				/*$("label[for='score']").text(data[0][0]);
				$("label[for='standev']").text(data[0][1]);
				$("#resultddiv").removeClass("makeHidden").addClass("makeVisible"); */
			},
			error:function(er) {
				console.log("error: "+er);
			}
		});	

	}
	
	$("#resetbttn_2").click(function(){ 
		$("#age").val("");
		$("#absences").val("");
		$("#famrel").val(-1);
		$("#goout").val(-1);
		$("#traveltime").val(-1);
		$("#freetime").val(-1);
		$("#dalc").val(-1);
		 
		$("#gradem1").val("");
		$("#gradem2").val("");
		$("#gradep1").val("");
		$("#gradep2").val("");
	});

	
	
	
	$("#multipleNavItem").click(function(){   
		$("#tabs-1").removeClass("show").removeClass("active");
		$("#tabs-2").addClass("show").addClass("active");
		$("#singleNavItem").removeClass("active");
		$("#multipleNavItem").addClass("active");
	});

	$("#singleNavItem").click(function(){   	  
		$("#tabs-1").addClass("show").addClass("active");
		$("#tabs-2").removeClass("show").removeClass("active");
		$("#singleNavItem").addClass("active");
		$("#multipleNavItem").removeClass("active");	  
	});
});