var popup={
	opne : function(btn) {
	 $(btn).magnificPopup({
	     type:'inline',
		 midClick: true,
		 preloader : false,
		 modal:true,
	     closeContentPos:true,
		 fixedContentPos:true,
				  alignTop : false,
				  showCloseBtn:true
				  });
		},
		close : function(btn) {
			$(btn).click(function(e) {
				e.preventDefault();
				$.magnificPopup.close();
			});
		}
 };