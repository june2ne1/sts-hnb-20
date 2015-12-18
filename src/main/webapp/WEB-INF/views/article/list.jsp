<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<LINK REL='stylesheet' type='text/css' href='${css}/board.css'/>	


<script type='text/javascript'>
	$(function() {
		board.init('1');
	});
	var board = {
		init : function(pageNo) {
			$.getJSON(context+'/article/list/'+pageNo,function(data) {
				var table = "<div id='boardList'><h1 align=center style='color:white;margin-bottom:30px'>자유게시판</h1>"
				+"<button id='btn_write'>글쓰기</button>"
				+"<TABLE id='tab_borderList'>"
				+"<TR ALIGN=CENTER><TD WIDTH=10%><B>번호</B></TD>"
				+"<TD WIDTH=40%><B>제 목</B></TD>"
				+"<TD WIDTH=20%><B>작성자</B></TD>"
				+"<TD WIDTH=20%><B>작성일</B></TD>"
				+"<TD WIDTH=8%><B>참조</B></TD></TR>"
				/* +"<c:forEach var='member' items='${memberList}' varStatus='status'><TR>"
				+"<TD WIDTH=10% ALIGN=CENTER>${status.index+1}</TD>"
				+"<TD WIDTH=20% ALIGN=CENTER>${member.id}</TD>"
				+"<TD WIDTH=20% ALIGN=CENTER><A HREF='BoardContent.jsp'>${member.name}</A></TD>"
				+"<TD WIDTH=30% ALIGN=LEFT>${member.email}</TD>"
				+"<TD WIDTH=18% ALIGN=CENTER>${member.regdate}</TD></TR></c:forEach>" */
				+"</TABLE></div>";
				$('.mainView').html(table);
				$('#btn_write').click(function() {
					board.write();
				});
			});
		},
		write : function() {
			alert('글쓰기 팝업을 띄워라 !!');
		}
	
	};
</script>