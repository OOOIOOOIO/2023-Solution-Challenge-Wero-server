package com.gdsc.wero.domain.diary.api;


import com.gdsc.wero.domain.diary.api.dto.request.DiaryGetDateReqDto;
import com.gdsc.wero.domain.diary.api.dto.request.DiarySaveReqDto;
import com.gdsc.wero.domain.diary.api.dto.response.DiaryInfoResDto;
import com.gdsc.wero.domain.diary.application.DiaryService;
import com.gdsc.wero.global.resolver.UserInfoFromHeader;
import com.gdsc.wero.global.resolver.UserInfoFromHeaderDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @ApiOperation(value = "diary 날짜 별 조회 api", notes = "diary 페이지 진입 시 및 캘린더 조회 시 diary 정보를 리턴한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "daily 정보 리턴 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("")
    public DiaryInfoResDto getDiary(@RequestBody DiaryGetDateReqDto diaryGetDateReqDto, @UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto) {

        String email = userInfoFromHeaderDto.getEmail();
        String provider = userInfoFromHeaderDto.getProvider();

        DiaryInfoResDto diary = diaryService.getDiary(diaryGetDateReqDto, email, provider);

        log.info("================ DIARY HAS BEEN SEARCHED =================");

        return diary;
    }

    @ApiOperation(value = "diary 저장 api", notes = "diary 작성 후 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "diary 저장 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("")
    public String saveDiary(@RequestBody DiarySaveReqDto diarySaveReqDto,@UserInfoFromHeader UserInfoFromHeaderDto userInfoFromHeaderDto) {

        String email = userInfoFromHeaderDto.getEmail();
        String provider = userInfoFromHeaderDto.getProvider();


        diaryService.saveDiary(diarySaveReqDto, email, provider);

        log.info("================== DIARY HAS BEEN SAVED===================");

        return "success";
    }

}
