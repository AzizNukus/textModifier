import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessorText {
   private StringBuilder text;
    private Pattern pattern;
    private Matcher matcher;
    private int lastEnd;
    public String textModifier(String input){
        input = removeExtraSpace(input);
        input = swapSymbolWithMinus(input);
        input = convertPlusToExclamation(input);
        input = countDigitAndAdd(input);
        return input;
    }
    private String removeExtraSpace(String input){
        pattern = Pattern.compile(" {2,}");
        matcher = pattern.matcher(input);
        text = new StringBuilder();
        lastEnd = 0;
        while (matcher.find()) {
            text.append(input, lastEnd, matcher.start());
//            System.out.println("Last end " + lastEnd+ " matcher.start " + matcher.start());
            text.append(" ");
//            System.out.println("text 2 " +text);
            lastEnd = matcher.end();
        }
        text.append(input.substring(lastEnd));// qalgan strokalardi qosamiz egerde regexke tuwra kelmese
//        System.out.println("text 3" +text);
        return text.toString();
    }
    private String swapSymbolWithMinus(String input){
        pattern = Pattern.compile("(\\S)-(\\S)");
        matcher = pattern.matcher(input);
        text = new StringBuilder(input);

        while (matcher.find()) {
            int leftIndex = matcher.start(1);
            int rightIndex = matcher.start(2);


            char leftChar = text.charAt(leftIndex);
            char rightChar = text.charAt(rightIndex);
            text.setCharAt(leftIndex, rightChar);
            text.setCharAt(rightIndex, leftChar);


            text.deleteCharAt(leftIndex + 1);
        }
        return text.toString();
    }
    private String convertPlusToExclamation(String input){
        pattern = Pattern.compile("\\+");
        matcher = pattern.matcher(input);
        lastEnd =0;
        text = new StringBuilder();
        while (matcher.find()){
            text.append(input, lastEnd, matcher.start()).append("!");
            lastEnd = matcher.end();
        }
        text.append(input.substring(lastEnd));
        return text.toString();
    }
    private String countDigitAndAdd(String input){
        pattern = Pattern.compile("\\d");
        matcher = pattern.matcher(input);
        text = new StringBuilder();
        boolean hasDigits = false;
        int sum = 0;
        lastEnd = 0;

        while (matcher.find()){
            if (!hasDigits) {
                hasDigits = true;
            }
            text.append(input, lastEnd, matcher.start());
            sum+= Character.getNumericValue(input.charAt(matcher.start()));
            lastEnd = matcher.end();
        }

        text.append(input.substring(lastEnd));
        if(hasDigits){
            text.append(" ").append(sum);
        }
        return text.toString();
    }
}
