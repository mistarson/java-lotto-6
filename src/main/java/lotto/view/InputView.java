package lotto.view;

import static lotto.constant.Constant.LOTTO_PURCHASE_MINIMUM_AMOUNT;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lotto.Lotto;
import lotto.validation.LottoInputValidator;

public class InputView {

    // TODO 재입력 while 문 format 맞추기
    public static int getLottoPurchaseAmount() {
        String lottoPurchaseAmount = "";
        boolean isNotRightInput = true;

        while (isNotRightInput) {
            System.out.println("구입금액을 입력해 주세요.");
            lottoPurchaseAmount = Console.readLine();
            try {
                LottoInputValidator.validateLottoPurchaseAmount(lottoPurchaseAmount);
                isNotRightInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return Integer.parseInt(lottoPurchaseAmount) / LOTTO_PURCHASE_MINIMUM_AMOUNT;
    }

    public static Lotto getWinningNumbers() {
        while (true) {
            System.out.println("당첨 번호를 입력해 주세요.");
            String inputNumbersString = Console.readLine();
            String[] split = inputNumbersString.split(",", -1);
            try {
                LottoInputValidator.validateLottoWinningNumbers(split);
                return createWinningNumbers(split);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Lotto createWinningNumbers(String[] split) {
        List<Integer> winningNumbers = new ArrayList<>();
        Stream.of(split).map(Integer::valueOf).forEach(winningNumbers::add);
        return Lotto.issueLotto(winningNumbers);
    }

    public static int getBonusNumber() {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String bonusNumber = Console.readLine();
                LottoInputValidator.validateBonusNumber(bonusNumber);
                return Integer.parseInt(bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
