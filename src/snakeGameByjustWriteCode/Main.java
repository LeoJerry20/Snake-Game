package snakeGameByjustWriteCode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
  SnakeGame snakegame=new SnakeGame(6,6);
    snakegame.start();
    }
}	
class SnakeGame{
	char[][] board;
	Queue<Node> snakeBody=new LinkedList<>();
	Queue<Node> food=new LinkedList<>();
	int currentRow=0;
	int currentCol=0;
	char currentHead='>';
	Random random=new Random();
	public SnakeGame(int row,int col) {
		board=new char[row][col];
		snakeBody.add(new Node(0,0));
		board[0][0]=currentHead;
		generateFood();
		placeFood();
	}
	public void generateFood() {
		food.add(new Node(1,0));
		food.add(new Node(2,4));
		food.add(new Node(3,1));
		food.add(new Node(4,4));
		food.add(new Node(5,5));
		}
	public void placeFood() {
		Node currentfood;
		if(!food.isEmpty()) {
			currentfood=food.poll();
		}
		else {
			do {
				currentfood=new Node(random.nextInt(board.length),random.nextInt(board.length));
			}while(board[currentfood.row][currentfood.col]!='\0');
		}
		board[currentfood.row][currentfood.col]='X';
	}
	public void start() {
		Scanner sc=new Scanner(System.in);
		printTable();
		while(true) {
		System.out.println("Enter any one Direction(U,D,R,L) :");
		char move=sc.next().toUpperCase().charAt(0);
		if(makeMove(move)) {
			printTable();
		}
		else {
			System.out.println("Game Over!");
			System.exit(0);
		}
		}
	}
	public boolean makeMove(int move) {
	int newRow=currentRow,newCol=currentCol;
	char newHead;
		switch(move){
	case 'U':
	{
		newRow--;
		newHead='^';
		break;
	}
	case 'D':
	{
		newRow++;
		newHead='v';
		break;
	}
	case 'R':
	{
		newCol++;
		newHead='>';
		break;
	}
	case 'L':
	{
		newCol--;
		newHead='<';
		break;
	}
	default :
		System.out.println("invalid Move. Try again!");
		return true;

	}
	if(!isValid(newRow,newCol))	{
		return false;
	}
	if(board[newRow][newCol]=='X') {
		placeFood();
	}
	else {
		Node tail=snakeBody.poll();
		board[tail.row][tail.col]='\0';
	}
	board[currentRow][currentCol]='.';
	snakeBody.add(new Node(newRow,newCol));
	currentRow=newRow;
	currentCol=newCol;
	currentHead=newHead;
	board[newRow][newCol]=currentHead;
	
		
		return true;	
	}
	public boolean isValid(int row ,int col) {
		return row>=0 && row<board.length && col>=0 && col<board[0].length && board[row][col]!='.';
	}
	public void printTable() {
		for(char[] row:board) {
			for(char col:row) {
				if(col=='\0') {
					System.out.print('■'+" ");
				}
				else {
					System.out.print(col+" ");
				}
					
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
    	
    	
    	
class Node{
	int row,col;
	public Node(int row,int col) {
		this.row=row;
		this.col=col;
	}
}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    }
//        SnakeGame snakeGame = new SnakeGame(6, 6);
//        snakeGame.start();
//    }
//}
//
//class SnakeGame{
//    private final char[][] board;
//    private final Queue<Node> snakeBody = new LinkedList<>();
//    private final Queue<Node> foodQueue = new LinkedList<>();
//    private final Random random = new Random();
//    private int currentRow = 0;
//    private int currentCol = 0;
//    private char currentHead = '>';
//    public SnakeGame(int rows, int cols) {
//        board = new char[rows][cols];
//        snakeBody.add(new Node(0, 0));
//        board[0][0] = currentHead;
//        generateFoodPositions();
//        placeFood();
//    }
//
//    public void start() {
//        Scanner scanner = new Scanner(System.in);
//        printBoard();
//
//        while (true) {
//            System.out.print("Enter move (U, D, L, R): ");
//            char move = scanner.next().toUpperCase().charAt(0);
//            if (makeMove(move)) {
//                printBoard();
//            }else{
//                System.out.println("Game Over!");
//                break;
//            }
//        }
//        scanner.close();
//    }
//
//    private boolean makeMove(char direction){
//        int newRow = currentRow, newCol = currentCol;
//        char newHead;
//        switch (direction) {
//            case 'U': newRow--; newHead = '^'; break;
//            case 'D': newRow++; newHead = 'v'; break;
//            case 'L': newCol--; newHead = '<'; break;
//            case 'R': newCol++; newHead = '>'; break;
//            default:
//                System.out.println("Invalid move! Use U, D, L, R.");
//                return true;
//        }
//
//        if (!isValidMove(newRow, newCol)) {
//            return false;
//        }
//
//        if (board[newRow][newCol] == 'X') {
//            placeFood();
//        }else{
//            Node tail = snakeBody.poll();
//            board[tail.row][tail.col] = '\0';
//        }
//
//        board[currentRow][currentCol] = '.';
//
//        snakeBody.add(new Node(newRow, newCol));
//        currentRow = newRow;  //updating curr row
//        currentCol = newCol;  //updating curr col
//        currentHead = newHead;  //updating curr head
//        board[newRow][newCol] = currentHead;
//
//        return true;
//    }
//
//    private boolean isValidMove(int row, int col) {
//        return row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] != '.';
//    }
//
//    private void generateFoodPositions() {
//        foodQueue.add(new Node(1, 0));
//        foodQueue.add(new Node(2, 2));
//        foodQueue.add(new Node(3, 4));
//        foodQueue.add(new Node(5, 2));
//        foodQueue.add(new Node(4, 5));
//    }
//
//    private void placeFood(){
//        Node food;
//        if (!foodQueue.isEmpty()) {
//            food = foodQueue.poll();
//        }else{
//            do {
//                food = new Node(random.nextInt(board.length), random.nextInt(board[0].length));
//            } while (board[food.row][food.col] != '\0');
//        }
//        board[food.row][food.col] = 'X';
//    }
//
//    private void printBoard() {
//        for (char[] row : board) {
//            for (char cell : row) {
//                System.out.print((cell == '\0' ? '■' : cell) + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//}
//
//class Node {
//    final int row, col;
//
//    Node(int row, int col) {
//        this.row = row;
//        this.col = col;
//    }
//}


