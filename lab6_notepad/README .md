# NotePad

## һ��ʱ�������
1.�ڲ�����������ʾʱ�����textview

> layout/noteslist_item.xml:

``` xml
<TextView
        android:layout_width="wrap_content"
        android:layout_height="?android:attr/listPreferredItemHeight"
        android:id="@+id/date"
        android:text="2017-04-26 20:20:20"
        android:layout_alignRight="@android:id/text1"
        android:paddingLeft="200dip"
        android:gravity="bottom"
        />
```
2.��NotesList��Activity��ΪͶӰ����һ��ʱ���
> NotesList:
``` xml
    private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_CREATE_DATE  //������ʱ����
    };
```
3.�޸�����������ز�����
>  NotesList:
``` 
        // Creates the backing adapter for the ListView.
       // SimpleCursorAdapter adapter
        adapter
            = new SimpleCursorAdapter(
                      this,                             // The Context for the ListView
                      R.layout.noteslist_item,          // Points to the XML for a list item
                      cursor,                           // The cursor to get items from
                      dataColumns            //���ͶӰ���е�����Ҫ����һ��
                      viewIDs                //ͬʱ�󶨵Ŀؼ���ҲҪ����
              );
``` 
���ʱ������Ӧ�Ĳ���

``` 
        //String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE} ;
        String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE, NotePad.Notes.COLUMN_NAME_CREATE_DATE} ; 
		
        //int[] viewIDs = { android.R.id.text1};
	    int[] viewIDs = { android.R.id.text1, R.id.date};
```

> Ч��
![image](image/date.png)

## ������ѯ����
1.��noteslist���activity��Ӧ�Ĳ˵���xml�ļ���������ʾ��������item
> list_options_menu.xml��

``` 
	//���������ӵ�һ��item
    <item android:id="@+id/menu_search"
        android:showAsAction="always"
        android:title="����" />
```
2,�¶���һ��ȫ�ֱ���������SearchView�������������Ū��ȫ�ֵı���

> NotesList:
``` 
    private SearchView searchView;
    //������
    private SimpleCursorAdapter adapter;
```
3.�ں����н�SearchView�󶨵���Ӧ�Ĳ˵���
 > NotesList -> public boolean onCreateOptionsMenu(Menu menu) 

``` stylus
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu from XML resource
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_options_menu, menu);
		setSearchView(menu);  //Ϊ�˵������õ�SearchView���
		...}
``` 
> setSearchView(menu)����

``` stylus
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setSearchView(Menu menu) {
        MenuItem menuItem = menu.getItem(0);
        searchView = new SearchView(this);
        menuItem.setActionView(searchView);

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("����");
        searchView.onActionViewCollapsed();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.onActionViewCollapsed();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!"".equals(newText)) {
                    //where����
                    String selection = NotePad.Notes.COLUMN_NAME_TITLE +" like ?";
                    String[] args = new String[]{newText+"%"};//�����Ĳ���
                    Cursor cursor = getContentResolver().query(getIntent().getData(),
                            PROJECTION,selection,args,null);
                    //���°��α�
                    adapter.swapCursor(null);
                    adapter.swapCursor(cursor);
                }
                return false;
            }
        });
    }
```
> Ч��
![image](image/search.png)



