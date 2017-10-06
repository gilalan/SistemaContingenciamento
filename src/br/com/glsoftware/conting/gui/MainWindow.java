package br.com.glsoftware.conting.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import br.com.glsoftware.conting.entities.Bank;
import br.com.glsoftware.conting.entities.BankInfo;
import br.com.glsoftware.conting.entities.Employee;
import br.com.glsoftware.conting.entities.Function;
import br.com.glsoftware.conting.entities.Schooling;
import br.com.glsoftware.conting.entities.State;
import br.com.glsoftware.conting.entities.Workplace;
import br.com.glsoftware.conting.fachada.Fachada;
import br.com.glsoftware.conting.interfaces.IFachada;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IFachada fachada;
	private JPanel contentPane;
	private JTable employeeTable;
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		fachada = new Fachada();
		
		setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		setTitle("SOLL - Contingenciamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 429);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmImportarParaBase = new JMenuItem("Importar Funcion\u00E1rios");
		mntmImportarParaBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        //".xls and .xlsx excel files", "xls", "xlsx");
		    		".xls excel files", "xls");
			    fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(contentPane.getParent());
				
				if (result == JFileChooser.APPROVE_OPTION) {
				    // user selects a file
					File selectedFile = fileChooser.getSelectedFile();
					readExcelFile(selectedFile);
				}
			}
		});
		
		JMenuItem mntmImportarCargos = new JMenuItem("Importar Cargos");
		mntmImportarCargos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        //".xls and .xlsx excel files", "xls", "xlsx");
		    		".xls excel files", "xls");
			    fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(contentPane.getParent());
				
				if (result == JFileChooser.APPROVE_OPTION) {
				    // user selects a file
					File selectedFile = fileChooser.getSelectedFile();
					readExcelFunctionsFile(selectedFile);
				}
			}
		});
		mnArquivo.add(mntmImportarCargos);
		
		JMenuItem mntmImportarDepartamentos = new JMenuItem("Importar Departamentos");
		mntmImportarDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        //".xls and .xlsx excel files", "xls", "xlsx");
		    		".xls excel files", "xls");
			    fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(contentPane.getParent());
				
				if (result == JFileChooser.APPROVE_OPTION) {
				    // user selects a file
					File selectedFile = fileChooser.getSelectedFile();
					readExcelWorkplacesFile(selectedFile);
				}
			}
		});
		mnArquivo.add(mntmImportarDepartamentos);
		mnArquivo.add(mntmImportarParaBase);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnFuncionrios = new JMenu("Funcion\u00E1rios");
		menuBar.add(mnFuncionrios);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
				List<Employee> employees = fachada.searchAll();
				String cols[] = {"Matrícula", "Nome", "PIS", "CPF", 
						"Função", "Cód Depto", "Depto", 
						"UF", "Data Admissão", "Data Nasc." };

				DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
				employeeTable.setModel(tableModel);

				//Object[] objs = {1, "Arsenal", 35, 11, 2, 2, 15, 30, 11, 19};
				for (Employee emp : employees){
					Object[] row = {
							emp.getMatriculation(), 
							emp.getName(),
							emp.getPis(),
							emp.getCpf(),
							emp.getFunction().getName(),
							emp.getWorkplace().getCode(),
							emp.getWorkplace().getName(),
							emp.getState().getAbbrev(),
							format1.format(emp.getAdmissionDate().getTime()),
							format1.format(emp.getBirthday().getTime())};
					tableModel.addRow(row);
				}
				
				CardLayout c = (CardLayout)contentPane.getLayout();
				c.show(contentPane, "employeesList");
			}			
		});
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)contentPane.getLayout();
				c.show(contentPane, "employeesCreate");
				
				
			}
		});
		mnFuncionrios.add(mntmNovo);
		
		mnFuncionrios.add(mntmListar);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(51, 0, 51), new Color(51, 0, 51), new Color(51, 0, 51), new Color(51, 0, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, "mainPanelGUI");
		
		JLabel lblTelaPrincipal = new JLabel("TELA PRINCIPAL");
		mainPanel.add(lblTelaPrincipal);
		
		JPanel panelListFunc = new JPanel();
		panelListFunc.setBackground(new Color(248, 248, 255));
		contentPane.add(panelListFunc, "employeesList");
		panelListFunc.setLayout(new BorderLayout(0, 0));
				
		JScrollPane scrollPane = new JScrollPane();
		panelListFunc.add(scrollPane);
		
		employeeTable = new JTable();
		
		//scrollPane.setViewportView(table);//ADD a table no ScrollPane
		scrollPane.setViewportView(employeeTable);
		//employeeTable.setFillsViewportHeight(true);
		
		JPanel panelNewFunc = new JPanel();
		panelNewFunc.setBackground(new Color(255, 51, 102));
		contentPane.add(panelNewFunc, "employeesCreate");
		
		JPanel panelSearchFunc = new JPanel();
		contentPane.add(panelSearchFunc, "employeesSearch");
	}
	
	/*
	 * Read excel employee file
	 * 
	 */
	private void readExcelFile(File selectedFile){
		
		List<Employee> excelEmployees;
		List<BankInfo> excelBankInfos;
		FileInputStream fis;
		HSSFWorkbook wb = null;
		try {
			
			excelEmployees = new ArrayList<Employee>();
			excelBankInfos = new ArrayList<BankInfo>();
			fis = new FileInputStream(selectedFile);
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			
			wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			//HSSFRow row;
		    //HSSFCell cell;
			//System.out.println("WorkBook: " + wb.getNameAt(0));
			System.out.println("Sheet: " + sheet.getSheetName());
			Employee employee;
			BankInfo bankInfo;
			
		    for(Row row : sheet) {
		    	employee = new Employee();
		    	bankInfo = new BankInfo();
		    	
		    	System.out.println("Row Number: " + row.getRowNum());
		    	if (!isHeaderOrEmptyRow(row)){
			        for (Cell cell : row){
		                if(cell != null) {
		                	if (cell.getCellTypeEnum() != CellType.BLANK)
	                				checkPositionAttributes(cell, employee, bankInfo);
	                		
		                }
			        }//for cell
		    	}
		        if (!employee.isEmpty()){
			        excelEmployees.add(employee);
			        bankInfo.setEmployee(employee);
			        excelBankInfos.add(bankInfo);
		        }
		        //System.out.println(); - 
		    }
//		    for (Employee emp : excelEmployees) {
//		    	System.out.println("Nome: " + emp.getName());
//		    	System.out.println("Admission: " + format1.format(emp.getAdmissionDate().getTime()));
//		    }
		    System.out.println("###### Size Employees: " + excelEmployees.size());
		    System.out.println("###### Size BankInfos: " + excelBankInfos.size());
		    
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			
			if (wb != null)
				try {
					wb.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	/*
	 * Read excel functions file (não quis generalizar por rapidez)
	 */
	private void readExcelFunctionsFile(File selectedFile) {
		
		List<Function> excelFunctions;
		Set<Function> excelSetFunctions;
		FileInputStream fis;
		HSSFWorkbook wb = null;
		try {
			
			excelFunctions = new ArrayList<Function>();
			excelSetFunctions = new HashSet<Function>();
			fis = new FileInputStream(selectedFile);
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			
			wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			//HSSFRow row;
		    //HSSFCell cell;
			//System.out.println("WorkBook: " + wb.getNameAt(0));
			System.out.println("Sheet: " + sheet.getSheetName());
			Function function;
			
		    for(Row row : sheet) {
		    	function = new Function();
		    	
		    	System.out.println("Row Number: " + row.getRowNum());
		    	if (!isHeaderOrEmptySingleColumn(row, "Função")){
			        for (Cell cell : row){
		                if(cell != null) {
		                	if (cell.getCellTypeEnum() != CellType.BLANK)
                				function.setName(cell.getStringCellValue());
	                		
		                }
			        }//for cell
		    	}
		        if (!function.isEmpty()){
			        excelFunctions.add(function);
			        excelSetFunctions.add(function);
		        }
		        //System.out.println(); - 
		    }
		    for (Function fun : excelFunctions) {
		    	System.out.println("Nome: " + fun.getName());
		    }
		    System.out.println("###### Size Functions: " + excelFunctions.size());
		    System.out.println("##### Size HashSet Function: " + excelSetFunctions.size());
		    
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			
			if (wb != null)
				try {
					wb.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	private void readExcelWorkplacesFile(File selectedFile) {
		
		List<Workplace> excelWorkplaces;
		FileInputStream fis;
		HSSFWorkbook wb = null;
		try {
			
			excelWorkplaces = new ArrayList<Workplace>();
			fis = new FileInputStream(selectedFile);
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			
			wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			System.out.println("Sheet: " + sheet.getSheetName());
			Workplace workplace;
			
		    for(Row row : sheet) {
		    	workplace = new Workplace();
		    	
		    	System.out.println("Row Number: " + row.getRowNum());
		    	if (!isHeaderOrEmptyDoubleColumn(row, "Cod Departamento", "Nome Departamento")){
			        for (Cell cell : row){
		                if(cell != null) {
		                	if (cell.getCellTypeEnum() != CellType.BLANK)
                				checkPositionAttrWorkplace(cell, workplace);
	                		
		                }
			        }//for cell
		    	}
		        if (!workplace.isEmpty()){
			        excelWorkplaces.add(workplace);
		        }
		        //System.out.println(); - 
		    }
		    for (Workplace wrk : excelWorkplaces) {
		    	System.out.println("Code: " + wrk.getCode());
		    	System.out.println("Nome: " + wrk.getName());
		    }
		    System.out.println("###### Size Workplaces: " + excelWorkplaces.size());
		    
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			
			if (wb != null)
				try {
					wb.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	private boolean isHeaderOrEmptyDoubleColumn(Row row, String string, String string2) {
		
		int count = 0;
		String[] headerCols = {
				string,
				string2
		};//verifica apenas as 2 primeiras colunas...
		if (row != null){
			
			for (int i = 0; i < headerCols.length; i++){
				
				if (row.getCell(i) == null)
					count++;
				else{
					if (row.getCell(i).getCellTypeEnum() == CellType.BLANK || 
						row.getCell(i).getStringCellValue().toLowerCase().equals(headerCols[i]))
						count++;
				}				
			}
			
			if (count >= 2){
				return true;
			}
		}
		return false;
	}

	private boolean isHeaderOrEmptyRow(Row row) {
		
		int count = 0;
		String[] headerCols = {
				"",
				"matricula",
				"nome",
				"função",
				"cod departamento",
				"nome departamento"
		};//verifica apenas as 6 primeiras colunas...
		if (row != null){
			
			for (int i = 0; i < headerCols.length; i++){
				
				if (row.getCell(i) == null)
					count++;
				else{
					if (row.getCell(i).getCellTypeEnum() == CellType.BLANK || 
						row.getCell(i).getStringCellValue().toLowerCase().equals(headerCols[i]))
						count++;
				}				
			}
			
			if (count >= 4){
				return true;
			}
		}
		return false;
	}
	
	private boolean isHeaderOrEmptySingleColumn(Row row, String columnName){
			
		if (row != null){
									
			if (row.getCell(0) == null)
				return true;
			else{
				if (row.getCell(0).getCellTypeEnum() == CellType.BLANK || 
					row.getCell(0).getStringCellValue().toLowerCase().equals(columnName))
					return true;
			}							
						
		}
		return false;
	}
	
	private void checkPositionAttrWorkplace(Cell cell, Workplace workplace) {
		
		int columnIndex = cell.getColumnIndex();
		//Posição 0 => Cod Departamento
		if (columnIndex == 0){
			workplace.setCode(cell.getStringCellValue());
		} else if (columnIndex == 1) { //Nome do Depto
			workplace.setName(cell.getStringCellValue());
		}
	}
	
	private void checkPositionAttributes(Cell cell, Employee employee, BankInfo bankInfo) {
		int columnIndex = cell.getColumnIndex();
		//System.out.println("row " + cell.getRowIndex());
		//Posição 0 => Matrícula
		if (columnIndex == 0){
			employee.setMatriculation(cell.getStringCellValue());
		} else if (columnIndex == 2) { //Nome do Funcionário
			employee.setName(cell.getStringCellValue());
		} else if (columnIndex == 3) { //Função - Nome
			employee.setFunction(new Function(cell.getStringCellValue()));
		} else if (columnIndex == 4) { //Departamento - Cód
			employee.setWorkplace(new Workplace(cell.getStringCellValue()));
		} else if (columnIndex == 5) { //Departamento - Nome
			if (employee.getWorkplace() == null){
				employee.setWorkplace(new Workplace(cell.getStringCellValue()));
			} else {
				(employee.getWorkplace()).setName(cell.getStringCellValue());
			}
		} else if (columnIndex == 6) { //Data Admissão
			if (HSSFDateUtil.isCellDateFormatted(cell)){
				Calendar cal = Calendar.getInstance();
    			//System.out.println(cell.getDateCellValue());
    			cal.setTime(cell.getDateCellValue());
    			employee.setAdmissionDate(cal);
			}
		} else if (columnIndex == 7) { //UF - abrev
			employee.setState(new State(cell.getStringCellValue()));
		} else if (columnIndex == 8) { //Data Nascimento
			if (HSSFDateUtil.isCellDateFormatted(cell)){
				Calendar cal = Calendar.getInstance();
				cal.setTime(cell.getDateCellValue());
				employee.setBirthday(cal);
			}
		} else if (columnIndex == 9) { //PIS
			employee.setPis(cell.getStringCellValue());
		} else if (columnIndex == 12) { //CPF
			employee.setCpf(cell.getStringCellValue());
		} else if (columnIndex == 13) { //(Informação Bancária) Nome Banco
			bankInfo.setBank(new Bank(cell.getStringCellValue()));
		} else if (columnIndex == 14) { //(Informação Bancária) Cód. Banco
			if (bankInfo.getBank() == null){
				bankInfo.setBank(new Bank());				
			} 
			(bankInfo.getBank()).setCode(cell.getStringCellValue());
		} else if (columnIndex == 15) { //(Informação Bancária) Agência
			bankInfo.setAgency(cell.getStringCellValue());
		} else if (columnIndex == 16) { //(Informação Bancária) Conta
			bankInfo.setAccount(cell.getStringCellValue());
		} else if (columnIndex == 17) { //(Informação Bancária) DV
			String dv = cell.getStringCellValue();
			if (dv.length() > 0)
				bankInfo.setVd(dv.charAt(0));
		} else if (columnIndex == 18) { //Salário
			employee.setSalary(BigDecimal.valueOf(cell.getNumericCellValue()));
		} else if (columnIndex == 19) { //Escolaridade
			employee.setSchooling(new Schooling(cell.getStringCellValue()));
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
