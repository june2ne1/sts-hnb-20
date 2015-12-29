<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<LINK REL='stylesheet' type='text/css' href='${css}/board.css'/>	
<style>
.white-popup {
  position: relative;
  background: #FFF;
  padding: 20px;
  width: auto;
  max-width: 500px;
  margin: 20px auto;
}
</style>

<div id="test-popup" class="white-popup mfp-hide">
 <TABLE WIDTH=620 HEIGHT=40 BORDER=0 CELLSPACING=1 CELLPADDING=1 ALIGN=CENTER>
	<TR BGCOLOR=#A0A0A0>
		<TD ALIGN=CENTER><FONT SIZE=4><B>게시판 ( 게시글 입력 )</B></FONT></TD>
	</TR>
</TABLE>

<FORM NAME="BoardWrite" METHOD=POST ACTION="BoardWriteProc.jsp">

<TABLE WIDTH=620 BORDER=1 CELLSPACING=0 CELLPADDING=2 ALIGN=CENTER>

	<TR>
		<TD WIDTH=120 ALIGN=CENTER><B>제목</B></TD>
		<TD WIDTH=500>
			<INPUT TYPE=TEXT NAME="subject" SIZE=70>
		</TD>
	</TR>
	
	<TR>
		<TD WIDTH=120 ALIGN=CENTER><B>내용</B></TD>
		<TD WIDTH=500>
			<TEXTAREA NAME="content" COLS=70 ROWS=8></TEXTAREA>
		</TD>
	</TR>
	
		
</TABLE>

</FORM>

<TABLE WIDTH=620 HEIGHT=50 BORDER=0 CELLSPACING=1 CELLPADDING=1 ALIGN=CENTER>

	<TR ALIGN=CENTER>
		<TD WIDTH=110 ALIGN=LEFT>
			<IMG id="go_list" SRC="${img}/btn_list.gif" STYLE=CURSOR:HAND>
		</TD>
		<TD WIDTH=400 ALIGN=CENTER>		
			<IMG id="save" SRC="${img}/btn_save.gif" STYLE=CURSOR:HAND>&nbsp;&nbsp;
			<IMG SRC="${img}/btn_cancel.gif" STYLE=CURSOR:HAND>
		</TD>
		<TD WIDTH=110 ALIGN=LEFT>&nbsp;</TD>   
	</TR>
	
</TABLE>
</div>
<a href="#test-popup" class="open-popup-link">Show inline popup</a>
<a href="mobile-friendly-page.html" data-mfp-src="#test-popup" class="open-popup-link">Show inline popup</a>

<script>
 $(function() {
  $('.open-popup-link').magnificPopup({
	  type:'inline',
	  midClick: true 
	  });
 });
 
</script>
<script type="text/javascript">
	$(function() {
		Article.list('142','1');
	});
	var Article = {
		list : function(themeNo,pageNo) {
			$.getJSON(context+'/article/list/'+themeNo+'/'+pageNo, function(data) {
				
				var table = '<div id="boardList"><TABLE><TR BGCOLOR=#A0A0A0><TD ALIGN=CENTER><FONT SIZE=4><B>자유게시판</B></FONT></TD></TR></TABLE>'
				+'<TABLE id="tab_boarderList">'
				+'<TR ALIGN=CENTER><TD WIDTH=8%><B>번호</B></TD>'
				+'<TD WIDTH=30%><B>제 목</B></TD>'
				+'<TD WIDTH=60%><B>내 용</B></TD></TR>';
				$.each(data, function(index,value) {
					table += '<TR><TD WIDTH=8% ALIGN=CENTER>'+(index+1)+'</TD>'
					+'<TD WIDTH=30% ALIGN=CENTER>'+this.usrSubject+'</TD>'
					+'<TD WIDTH=60% ALIGN=CENTER>'+this.usrContent+'</TD></TR>'
				});
				
				table +='</TABLE></div>';
				table +='<div style="width:300px;margin:auto"><h1><a href="'+context+'/article/write" id=dialog-model">글쓰기</a></h1></div>';
				$('.mainView').html(table);
					
			});
		},
		write : function() {
			alert('12/18 현재 팝업으로 할 거 구현안됬음.');
		}
		
	};
</script>