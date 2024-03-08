package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoImplTest {
    @Autowired
    private BoardDao boardDao;

    private final String WRITER = "qwer";

    @Test
    public void countTest() throws Exception {
        int initialCount = boardDao.totCount();

        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        assertEquals(boardDao.totCount(), initialCount + 1);

        id = (int) (Math.random() * 1000);
        boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        assertEquals(boardDao.totCount(), initialCount + 2);
    }

    @Test
    public void deleteAdminTest() throws Exception {
        int initialCount = boardDao.totCount();

        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));

        List<BoardDto> postList = boardDao.selectAll();
        assertEquals(1, boardDao.deleteForAdmin(postList.get(0).getBno()));
        assertEquals(initialCount, boardDao.totCount());

        id = (int) (Math.random() * 1000);
        boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        id = (int) (Math.random() * 1000);
        boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        postList = boardDao.selectAll();
        assertEquals(1, boardDao.deleteForAdmin(postList.get(0).getBno()));
        assertEquals(boardDao.totCount(), initialCount + 1);
    }

    @Test
    public void deleteTest() throws Exception {
        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        int currentCnt = boardDao.totCount();

        Integer bno = boardDao.selectAll().get(0).getBno();
        String writer = boardDto.getWriter();
        BoardDto myPost = new BoardDto(bno, writer);
        assertEquals(1, boardDao.deleteMyPost(myPost));
        assertEquals(boardDao.totCount(), currentCnt - 1);

        id = (int) (Math.random() * 1000);
        boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        currentCnt = boardDao.totCount();

        bno = boardDao.selectAll().get(0).getBno();
        writer = boardDto.getWriter() + "222";
        myPost = new BoardDto(bno, writer);
        assertEquals(0, boardDao.deleteMyPost(myPost));
        assertEquals(boardDao.totCount(), currentCnt);

        bno = boardDao.selectAll().get(0).getBno();
        writer = boardDto.getWriter();
        myPost = new BoardDto(bno, writer);
        assertEquals(1, boardDao.deleteMyPost(myPost));
        assertEquals(boardDao.totCount(), currentCnt - 1);

        id = (int) (Math.random() * 1000);
        boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        currentCnt = boardDao.totCount();

        bno = 0;
        writer = boardDto.getWriter();
        myPost = new BoardDto(bno, writer);
        assertEquals(0, boardDao.deleteMyPost(myPost));
        assertEquals(boardDao.totCount(), currentCnt);
    }

    @Test
    public void insertTest() throws Exception {
        int initialCnt = boardDao.totCount();

        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        assertEquals(boardDao.totCount(), initialCnt + 1);

        int currentCnt = boardDao.totCount();
        id = (int) (Math.random() * 1000);
        boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));

        id = (int) (Math.random() * 1000);
        boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        assertEquals(boardDao.totCount(), currentCnt + 2);
    }

    @Test
    public void selectAllTest() throws Exception {
        int currentCnt = boardDao.totCount();

        List<BoardDto> list = boardDao.selectAll();
        assertEquals(list.size(), currentCnt);

        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));

        list = boardDao.selectAll();
        assertEquals(list.size(), currentCnt + 1);

        id = (int) (Math.random() * 1000);
        boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        list = boardDao.selectAll();
        assertEquals(list.size(), currentCnt + 2);
    }

    @Test
    public void selectTest() throws Exception {
        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));

        Integer bno = boardDao.selectAll().get(0).getBno();
        boardDto.setBno(bno);
        BoardDto currentPost = boardDao.selectPost(bno);
        assertEquals(boardDto, currentPost);
    }

    @Test
    public void selectPageTest() throws Exception {
        for (int i = 1; i <= 10; i++) {
            BoardDto boardDto = new BoardDto("title" + i, "no content" + i, WRITER);
            boardDao.insertPost(boardDto);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<BoardDto> list = boardDao.selectPage(map);
        assertEquals("title10", list.get(0).getTitle());
        assertEquals("title9", list.get(1).getTitle());
        assertEquals("title8", list.get(2).getTitle());

        map = new HashMap<>();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = boardDao.selectPage(map);
        assertEquals("title10", list.get(0).getTitle());

        map = new HashMap<>();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = boardDao.selectPage(map);
        assertEquals("title3", list.get(0).getTitle());
        assertEquals("title2", list.get(1).getTitle());
        assertEquals("title1", list.get(2).getTitle());
    }

    @Test
    public void updateTest() throws Exception {
        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));

        Integer bno = boardDao.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);
        boardDto.setBno(bno);
        boardDto.setTitle("yes title" + id);
        assertEquals(1, boardDao.updatePost(boardDto));

        BoardDto currentPost = boardDao.selectPost(bno);
        assertEquals(boardDto, currentPost);
    }

    @Test
    public void increaseViewCntTest() throws Exception {
        int currentCnt = boardDao.totCount();

        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, WRITER);
        assertEquals(1, boardDao.insertPost(boardDto));
        assertEquals(boardDao.totCount(), currentCnt + 1);

        Integer bno = boardDao.selectAll().get(0).getBno();
        assertEquals(1, boardDao.increaseViewCnt(bno));

        boardDto = boardDao.selectPost(bno);
        assertNotNull(boardDto);
        assertEquals(1, boardDto.getViewCnt());

        assertEquals(1, boardDao.increaseViewCnt(bno));
        boardDto = boardDao.selectPost(bno);
        assertNotNull(boardDto);
        assertEquals(2, boardDto.getViewCnt());
    }

    @Test
    public void searchTest() throws Exception {
        List<SearchOption> searchOptions = new ArrayList<>();

        searchOptions.add(new SearchOption(1, 10, "title1", "T"));
        searchOptions.add(new SearchOption(1, 10, "zxcv", "W"));
        searchOptions.add(new SearchOption(1, 10, "content1", ""));

        for (SearchOption searchOption : searchOptions) {
            int postCnt = boardDao.searchPostCnt(searchOption);
            System.out.println("postCnt = " + postCnt);

            int postSeq = 0;
            List<BoardDto> postList = boardDao.searchPostList(searchOption);
            while (!CollectionUtils.isEmpty(postList)) {
                for (BoardDto post : postList) {
                    boolean hasTitle = post.getTitle().contains(searchOption.getKeyword());
                    boolean hasWriter = post.getWriter().contains(searchOption.getKeyword());
                    boolean hasContent = post.getContent().contains(searchOption.getKeyword());

                    if ("T".equals(searchOption.getField())) {
                        assertTrue(hasTitle);
                    } else if ("W".equals(searchOption.getField())) {
                        assertTrue(hasWriter);
                    } else {
                        assertTrue(hasTitle || hasContent);
                    }

                    postSeq++;
                }

                searchOption.setPage(searchOption.getPage() + 1);
                postList = boardDao.searchPostList(searchOption);
            }

            assertEquals(postCnt, postSeq);
        }
    }
}