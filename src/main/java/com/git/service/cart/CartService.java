package com.git.service.cart;

import com.git.domain.cart.CartItem;
import com.git.domain.item.Item;
import com.git.domain.member.Member;
import com.git.exception.cart.CartItemNotFoundException;
import com.git.exception.item.ItemNotFoundException;
import com.git.exception.member.MemberNotFoundException;
import com.git.repository.cart.CartItemRepository;
import com.git.repository.item.ItemRepository;
import com.git.repository.member.MemberRepository;
import com.git.request.cart.CartCreateRequest;
import com.git.response.cart.CartCreateResponse;
import com.git.response.cart.CartDetailResponse;
import com.git.response.cart.CartUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public CartCreateResponse cartSave(Long memberId, CartCreateRequest request) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        Item item = itemRepository.findById(request.getItemId()).orElseThrow(ItemNotFoundException::new);

        CartItem cartItem = cartItemRepository.save(CartItem.builder()
                .item(item)
                .cart(member.getCart())
                .color(request.getColor())
                .size(request.getSize())
                .count(request.getCount())
                .build());
        return CartCreateResponse.toSave(cartItem);
    }

    @Transactional
    public CartDetailResponse myCart(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        List<CartItem> cartItems = member.getCart().getCartItems();
        return CartDetailResponse.toSave(cartItems);
    }

    @Transactional
    public CartUpdateResponse updateCartItemOption(Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);

    }
}






























