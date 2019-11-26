import java.util.Stack;

class reverseStack {

    private static Stack<Character> st = new Stack<>();

    private static void insertAtBottom(char x)
    {
        if(st.isEmpty())
            st.push(x);
        else
        {
            char a = st.peek();
            st.pop();
            insertAtBottom(x);
            st.push(a);
        }
    }

    private static void reverse()
    {
        if(st.size() > 0)
        {
            char x = st.peek();
            st.pop();
            reverse();
            insertAtBottom(x);
        }
    }

    public static void main(String[] args)
    {
        st.push('1');
        st.push('2');
        st.push('3');
        st.push('4');

        System.out.println("Original Stack");
        System.out.println(st);
        reverse();
        System.out.println("Reversed Stack");
        System.out.println(st);
    }
}