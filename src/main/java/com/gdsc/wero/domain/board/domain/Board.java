package com.gdsc.wero.domain.board.domain;

import com.gdsc.wero.domain.board.api.dto.request.BoardReqDto;
import com.gdsc.wero.domain.common.BaseTimeEntity;
import com.gdsc.wero.domain.reply.domain.Reply;
import com.gdsc.wero.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    private String title;
    private String contents;

    private String imgLink;
    private String imgName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> reply = new ArrayList<>();

    @Builder
    private Board(String title, String contents, String imgLink, String imgName, User user) {
        this.title = title;
        this.contents = contents;
        this.imgLink = imgLink;
        this.imgName = imgName;
        this.user = user;
    }

    /**
     * Board 생성 메서드
     */
    public static Board createBoard(BoardReqDto boarReqDto, String imgLink, String imgName,User user){
        return Board.builder()
                .title(boarReqDto.getTitle())
                .contents(boarReqDto.getContents())
                .imgLink(imgLink)
                .imgName(imgName)
                .user(user)
                .build();
    }

    /**
     * Board 수정 메서드
     */
    public void updateBoard(BoardReqDto boardReqDto, String imgLink, String imgName) {
        this.title = boardReqDto.getTitle();
        this.contents = boardReqDto.getContents();
        this.imgLink = imgLink;
        this.imgName = imgName;

    }


}
