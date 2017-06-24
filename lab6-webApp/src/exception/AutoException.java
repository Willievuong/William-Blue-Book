package exception;

public class AutoException extends Exception {
	private int errorno;
	private String errormsg;

	public AutoException() {
	}
	
	public AutoException(int errorno) {
		this.errorno = errorno;
		printProblem();
	}
	
	public AutoException(String errormsg) {
		this.errormsg = errormsg;
		printProblem();
	}
	
	public AutoException(int errorno, String errormsg) {
		this.errorno = errorno;
		this.errormsg = errormsg;
		printProblem();
	}

	public int getErrorno() {
		return errorno;
	}

	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public void printProblem(){
		System.out.println("FixProblems [errorno=" + errorno + ", errormsg=" + errormsg); 
	}

	
	public void fix(int errorno) {
		Fix1to100 f1 = new Fix1to100();

		switch (errorno) {
		case 0:
			f1.fix0(errorno);
			f1.log(errorno);
			break;

		case 1:
			f1.fix1(errorno);
			f1.log(errorno);
			break;

		case 2:
			f1.fix2(errorno);
			f1.log(errorno);
			break;

		case 3:
			f1.fix3(errorno);
			f1.log(errorno);
			break;

		case 4:
			f1.fix4(errorno);
			f1.log(errorno);
			break;

		case 5: 
			f1.fix5(errorno);
			f1.log(errorno);
			break;
		}
	}
	
	
}


//Editted By William Vuong
//Code Referred from Bob Singh 