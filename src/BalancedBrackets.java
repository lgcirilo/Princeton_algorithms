import java.util.*;
class BalancedBrackets {

    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        Stack stack = new Stack<Character>();
        Character current = null;

        while (sc.hasNext()) {
            String input=sc.next();
            for(int i = 0; i < input.length(); i++) {
                current = input.charAt(i);
                switch(current) {
                    case '}':
                        if(!stack.empty() && stack.peek().equals('{')) {
                            stack.pop();
                        } else {
                            stack.push(current);
                        }
                        break;
                    case ')':
                        if(!stack.empty() && stack.peek().equals('(')) {
                            stack.pop();
                        } else {
                            stack.push(current);
                        }
                        break;
                    case ']':
                        if(!stack.empty() && stack.peek().equals('[')) {
                            stack.pop();
                        } else {
                            stack.push(current);
                        }
                        break;
                    default:
                        stack.push(current);
                        break;
                }
            }
            System.out.println(stack.empty());
            stack = new Stack<String>();
        }
    }
}



