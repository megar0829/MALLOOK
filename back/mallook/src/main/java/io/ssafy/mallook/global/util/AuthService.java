package io.ssafy.mallook.global.util;

import io.ssafy.mallook.domain.order.dao.OrderRepository;
import io.ssafy.mallook.domain.order.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final ScriptRepository scriptRepository;
    private final OrderRepository orderRepository;

    public boolean authorizeToReadScriptDetail(UUID memberId, Long scriptId) {
        log.info("내가 쓴 글인지 확인 시작");
        return scriptRepository.findByIdAndStatusTrue(scriptId)
                .orElseThrow().isWrittenByTargetMember(memberId);
    }

    public boolean authorizeToReadOrderDetail(UUID memberId, Long orderId) {
        return orderRepository.findByIdAndStatusTrue(orderId)
                .orElseThrow().isCreateByTargetMember(memberId);
    }

    public boolean authorizeToDeleteScript(UUID memberId, ScriptDeleteListDto scriptDeleteListDto) {
        log.info("내가 쓴 글인지 확인 시작");
        List<Long> scriptIdList = scriptDeleteListDto.toDeleteList();

        return scriptIdList.stream()
                .map(scriptRepository::findByIdAndStatusTrue)
                .allMatch(scriptOptional -> scriptOptional
                        .filter(script -> script.isWrittenByTargetMember(memberId))
                        .isPresent());
    }

    public boolean authorizeToDeleteOrder(UUID memberId, OrderDeleteDto orderDeleteDto) {
        log.info("메서드 진입");
        List<Long> deleteList = orderDeleteDto.deleteList();

        return deleteList.stream()
                .map(orderRepository::findByIdAndStatusTrue)
                .allMatch(orderOptional -> orderOptional
                        .filter(order -> order.isCreateByTargetMember(memberId))
                        .isPresent());
    }
}
