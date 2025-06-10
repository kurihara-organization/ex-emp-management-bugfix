package com.example.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * アプリケーション全体の例外を処理するクラス
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 404 Not Found エラー処理
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(NoHandlerFoundException ex, Model model) {
        model.addAttribute("message", "ページが見つかりません。URLをご確認ください。");
        return "error/404";
    }

    // その他の予期しないエラー処理（500 Internal Server Error）
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("message", "ただいまメンテナンス中です。しばらくしてから再度お試しください。");
        return "error/500";
    }
}
