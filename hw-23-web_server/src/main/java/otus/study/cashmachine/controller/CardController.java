package otus.study.cashmachine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import otus.study.cashmachine.bank.dto.CardBalanceDTO;
import otus.study.cashmachine.bank.dto.GetMoneyDTO;
import otus.study.cashmachine.bank.dto.PinDTO;
import otus.study.cashmachine.bank.dto.PutMoneyDTO;
import otus.study.cashmachine.bank.service.CardService;

import java.math.BigDecimal;

@Controller
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/balance-form")
    public ModelAndView getBalanceForm() {
        ModelAndView mav = new ModelAndView("balance-form");
        mav.addObject("getBalanceDto", new CardBalanceDTO());
        return mav;
    }

    @GetMapping("/balance")
    public ModelAndView getBalance(@ModelAttribute("card") CardBalanceDTO getBalanceDto) {
        ModelAndView mav = new ModelAndView("balance");
        BigDecimal balance = cardService.getBalance(getBalanceDto.getNumber(), getBalanceDto.getPin());
        mav.addObject("balance", balance);
        return mav;
    }

    @GetMapping("/withdrawal-form")
    public ModelAndView getMoneyForm() {
        ModelAndView mav = new ModelAndView("withdrawal-form");
        mav.addObject("getMoneyDto", new GetMoneyDTO());
        return mav;
    }

    @PostMapping("/withdrawal")
    public ModelAndView getMoney(@ModelAttribute("card") GetMoneyDTO getMoneyDto) {
        ModelAndView mav = new ModelAndView("balance");
        BigDecimal balance = cardService.getMoney(getMoneyDto.getNumber(), getMoneyDto.getPin(), getMoneyDto.getAmount());
        mav.addObject("balance", balance);
        return mav;
    }

    @GetMapping("/put-form")
    public ModelAndView putMoneyForm() {
        ModelAndView mav = new ModelAndView("put-form");
        mav.addObject("putMoneyDto", new PutMoneyDTO());
        return mav;
    }

    @PostMapping("/put")
    public ModelAndView putMoney(@ModelAttribute("card") PutMoneyDTO putMoneyDto) {
        ModelAndView mav = new ModelAndView("balance");
        BigDecimal balance = cardService.putMoney(putMoneyDto.getNumber(), putMoneyDto.getPin(), putMoneyDto.getAmount());
        mav.addObject("balance", balance);
        return mav;
    }

    @GetMapping("/change-pin-form")
    public ModelAndView changePinForm() {
        ModelAndView mav = new ModelAndView("change-pin-form");
        mav.addObject("pinDto", new PinDTO());
        return mav;
    }

    @PostMapping("/change-pin")
    public ModelAndView changePin(@ModelAttribute("card") PinDTO pinDto) {
        ModelAndView mav = new ModelAndView("change-pin");
        boolean isChanged = cardService.changePin(pinDto.getNumber(), pinDto.getOldPin(), pinDto.getNewPin());
        mav.addObject("isChanged", isChanged);
        return mav;
    }
}
