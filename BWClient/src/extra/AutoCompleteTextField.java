package extra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Window;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import mainframeui.TField;


public class AutoCompleteTextField
{
	private TextField textField;
	private final static int LIST_MAX_SIZE = 6;
	private final static int LIST_CELL_HEIGHT = 24;
	
	private HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();

	private ObservableList<String> showCacheDataList = FXCollections.<String> observableArrayList();

	private List<String> cacheDataList = new ArrayList<String>()
	{
		private static final long serialVersionUID = 281687373227150590L;

		@Override
		public int indexOf(Object searchContext)
		{
			showCacheDataList.clear();
			if(null == searchContext || searchContext.toString().equals("")) {
				return -1;
			}
			int size = cacheDataList.size();
			for (int i = 0; i < size; i++)
			{
				char[] charArry = cacheDataList.get(i).toCharArray();
				StringBuilder sbPinyin = new StringBuilder();
				String indexPinyin = new String();
				for (char ch : charArry)
				{
					try
					{
						String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(ch, pinyinFormat);
						sbPinyin.append(null != pinyin ? pinyin[0] : ch);
						if(null != pinyin) {
							indexPinyin = indexPinyin + pinyin[0].charAt(0);
						}
					} catch (BadHanyuPinyinOutputFormatCombination e)
					{
						sbPinyin.append(ch);
					}
				}
				if (cacheDataList.get(i).contains(searchContext.toString())|| sbPinyin.toString().contains(searchContext.toString()) || indexPinyin.contains(searchContext.toString()))
				{
					showCacheDataList.add(cacheDataList.get(i));
				}
			}
			return -1;
		};
	};
	private SimpleStringProperty inputContent = new SimpleStringProperty();

	private Popup popShowList = new Popup();

	private ListView<String> autoTipList = new ListView<String>();

	AutoCompleteTextField(TField textField, List<String> cacheDataList)
	{
		if (null == textField)
		{
			throw new RuntimeException("textField 涓嶈兘涓虹┖");
		}
		this.textField = textField;
		if (null != cacheDataList)
		{
			this.cacheDataList.addAll(cacheDataList);
		}
		configure();
		confListnenr();
	}

	AutoCompleteTextField(TField textField)
	{
		this(textField, null);
	}

	public void setCacheDataList(List<String> cacheDataList)
	{
		this.cacheDataList.clear();
		this.cacheDataList.addAll(cacheDataList);
	}

	private void confListnenr()
	{
		textField.textProperty().bindBidirectional(inputContent);

		textField.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				cacheDataList.add(inputContent.get()); // setOnAction浜嬩欢鍚庢墠浼氱敓鏁堬紝姝ゅ鏄偣鍑绘寜閽椂灏嗘枃鏈涓暟鎹瓨鍏ュ埌cacheDataList锟�?
				removeDuplicate(cacheDataList);
			}
		});

		inputContent.addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue)
			{
				configureListContext(newValue);  
			}
		});

		autoTipList.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				selectedItem();
			}
		});

		autoTipList.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				if(event.getCode() == KeyCode.ENTER) 
				{
					selectedItem();
				}
			}
		});
	}
	
	private void selectedItem() {
		inputContent.set(autoTipList.getSelectionModel().getSelectedItem());
		textField.end();
		popShowList.hide();
	}
	private void configureListContext(String tipContent)
	{
		cacheDataList.indexOf(tipContent);
		if(!showCacheDataList.isEmpty()) {
			showTipPop();
		} else {
			popShowList.hide();
		}
	}
	

	private void configure()
	{
		pinyinFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

		popShowList.setAutoHide(true);
		popShowList.getContent().add(autoTipList);
  		autoTipList.setItems(showCacheDataList);
	}
	
	public void removeDuplicate(List<String> cacheDataList2) {
		HashSet<String> h  =   new  HashSet<String>(cacheDataList2); 
	    cacheDataList2.clear(); 
	    cacheDataList2.addAll(h); 
	}
	
	public final Window getWindow()
	{
		return getScene().getWindow();
	}

	public final Scene getScene()
	{
		return textField.getScene();
	}
	public final void showTipPop()
	{
		autoTipList.setPrefWidth(textField.getWidth() - 3);
		if(showCacheDataList.size() < LIST_MAX_SIZE) {
			autoTipList.setPrefHeight(showCacheDataList.size() * LIST_CELL_HEIGHT + 3);
		} else {
			autoTipList.setPrefHeight(LIST_MAX_SIZE * LIST_CELL_HEIGHT + 3);
		}
		Window window = getWindow();
		Scene scene = getScene();
		Point2D fieldPosition = textField.localToScene(0, 0);
		popShowList.show(window, window.getX() + fieldPosition.getX() + scene.getX(), window.getY() + fieldPosition.getY() + scene.getY() + textField.getHeight());
		autoTipList.getSelectionModel().clearSelection();
		autoTipList.getFocusModel().focus(-1);
	}
}
