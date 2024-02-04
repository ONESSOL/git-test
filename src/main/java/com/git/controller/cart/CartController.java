package com.git.controller.cart;

import com.git.config.SecurityUtil;
import com.git.request.cart.CartCreateRequest;
import com.git.response.cart.CartCreateResponse;
import com.git.response.cart.CartDetailResponse;
import com.git.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/save")
    public ResponseEntity<CartCreateResponse> saveCart(@RequestBody CartCreateRequest request) {
        return ResponseEntity.ok(cartService.cartSave(SecurityUtil.currentMemberId(), request));
    }

    @GetMapping("/my_cart")
    public ResponseEntity<CartDetailResponse> myCart() {
        return ResponseEntity.ok(cartService.myCart(SecurityUtil.currentMemberId()));
    }
}
