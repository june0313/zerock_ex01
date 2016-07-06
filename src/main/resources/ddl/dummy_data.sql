INSERT INTO tbl_board (title, content, writer)
  (SELECT title, content, writer FROM tbl_board);