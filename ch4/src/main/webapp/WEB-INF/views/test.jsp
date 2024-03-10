<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>comment test</title>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2>Comment Test (BNO : <span id="bno"></span>)</h2>
WRITE : <input type="text" id="writeForm" data-cno="" />
<br/><br/><button type="button" id="homeBtn">HOME</button>
<button type="button" id="listBtn" style="margin-left: 20px;">LIST</button>
<h2>Data From Server :</h2>
<div id="commentList"></div>
<script>
const bno = 298;

$(document).ready(() => {
    $('#bno').text(bno);
    showList();
    const commentList = $('#commentList');

    $('#homeBtn').click(() => {
        if (confirm('Return Home?')) {
            location.href = '<c:url value='/'/>';
        }
    });

    $('#listBtn').click(showList);

    $(commentList).on('click', '.delBtn', (e) => {
        const cno = $(e.target).parent().data('cno');   // 사용자 정의 태그 data-이름 사용 시 dataset이라는 Map에 이름:값 형태로 저장됨
        const bno = $(e.target).parent().data('bno');   // $(태그).data('이름'[, 값]) 형태로 조회 및 수정 가능
        $.ajax({
            type: 'DELETE',
            url: '/comments/' + cno + '?bno=' + bno,
            async: false,
            success: result => {
                alert(result);
                showList();
            },
            error: () => alert('error')
        });
    });

    $(commentList).on('click', '.modBtn', (e) => {
        const cno = $(e.target).parent().data('cno');
        const comment = $('.comment', $(e.target).parent()).text(); // $(A, B) : B태그의 하위에 있는 A태그

        $('#writeForm')
            .off()
            .attr('data-cno', cno)
            .val(comment)
            .keypress(modify)
            .focus();
    });
});

const toHTML = comments => {
    let html = '<ul>';

    for (const comment of comments) {
        html += '<li data-cno=' + comment.cno
            + ' data-pcno=' + comment.parentCno
            + ' data-bno=' + comment.bno + '>'
            + 'WRITER : <span class="writer">' + comment.writer + '</span>&ensp;/&ensp;'
            + 'COMMENT : <span class="comment">' + comment.comment + '</span>&ensp;/&ensp;'
            + 'REGDATE : ' + new Date(comment.regDate) + '&ensp;/&ensp;';

        if (comment.updDate != null) {
            html += 'UPDDATE : ' + new Date(comment.updDate) + '&ensp;/&ensp;';
        }

        html += '<button type="button" class="modBtn">수정</button>&ensp;/&ensp;'
            + '<button type="button" class="delBtn">삭제</button>'
            + '</li>';
    }

    return html + '</ul>';
};

const showList = () => {
    $.ajax({
        type: 'GET',
        url: '/comments?bno=' + bno,
        dataType: 'json',
        success: result => {
            $('#commentList').html(toHTML(result));
            $('#writeForm')
                .off()
                .attr('data-cno', '')
                .val('')
                .keypress(write);
        },
        error: () => alert('error')
    });
};

const write = (e) => {
    const comment = $(e.target).val().trim();
    if (e.keyCode != 13 || !comment) {
        return;
    }
    $(e.target).val('');

    $.ajax({
        type: 'POST',
        url: '/comments',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify({bno: bno, comment: comment}),
        success: result => {
            alert(result);
            showList();
        },
        error: () => alert('error')
    });
};

const modify = (e) => {
    const comment = $(e.target).val().trim();
    if (e.keyCode != 13 || !comment) {
        return;
    }
    $(e.target).val('');

    $.ajax({
        type: 'PATCH',
        url: '/comments/' + $(e.target).data('cno'),
        headers: {'content-type': 'application/json'},
        data: JSON.stringify({comment: comment}),
        success: result => {
            alert(result);
            showList();
        },
        error: () => alert('error')
    });
};
</script>
</body>
</html>