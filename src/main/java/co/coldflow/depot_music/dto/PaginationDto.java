package co.coldflow.depot_music.dto;

public class PaginationDto {
    private int BLOCK_SIZE = 10;

    private int currentPage; /*현재 페이지 번호*/
    private int totalPageCount; /*모든 페이지 수*/

    private int totalBocks; /*모든 블록 수*/
    private int currentBlock; /*현재 블록 번호*/

    private int startPage; /*시작 버튼 클릭시 이동될 페이지번호*/
    private int endPage; /*끝 버튼 클릭시 이동될 페이지번호*/
    private int prevBlock; /*이전 버튼 클릭시 이동될 페이지번호*/
    private int nextBlock; /*다음 버튼 클릭시 이동될 페이지번호*/

    public PaginationDto(int page, int totalPageCount, int size) {
        this.currentPage = page + 1;
        this.totalPageCount = totalPageCount;

        this.totalBocks = (int) Math.ceil((totalPageCount * 1.0)/BLOCK_SIZE);
        this.currentBlock = (int) Math.ceil((this.currentPage * 1.0)/BLOCK_SIZE);

        this.startPage = ((currentBlock - 1) * BLOCK_SIZE + 1);

        this.endPage = (startPage + BLOCK_SIZE - 1);
        if(endPage > totalPageCount) endPage = totalPageCount;

        this.prevBlock = (currentBlock * BLOCK_SIZE) - BLOCK_SIZE;
        if(prevBlock < 1) prevBlock = 1;

        this.nextBlock = (currentBlock * BLOCK_SIZE) + 1;
        if(nextBlock > totalPageCount) nextBlock = totalPageCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getPrevBlock() {
        return prevBlock;
    }

    public int getNextBlock() {
        return nextBlock;
    }
}
