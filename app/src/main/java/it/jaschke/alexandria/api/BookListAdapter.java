package it.jaschke.alexandria.api;


import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.jaschke.alexandria.R;
import it.jaschke.alexandria.data.AlexandriaContract;

/**
 * Created by saj on 11/01/15.
 */
public class BookListAdapter extends CursorAdapter {

    private Context mAdapterContext;
    private Drawable mPlaceholderDrawable;
    private Drawable mErrorDrawable;
    public BookListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.mAdapterContext = context;
        this.mPlaceholderDrawable = ContextCompat.getDrawable( context, R.drawable.ic_launcher );
        this.mErrorDrawable = ContextCompat.getDrawable(mAdapterContext, android.R.color.holo_red_light);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String imgUrl = cursor.getString(cursor.getColumnIndex(AlexandriaContract.BookEntry.IMAGE_URL));
        if( imgUrl != null ) {
            Picasso.with(mAdapterContext)
                    .load(imgUrl)
                    .placeholder(mPlaceholderDrawable)
                    .error(mErrorDrawable)
                    .into(viewHolder.fullBookCoverImageView);
        }
        else {
            viewHolder.fullBookCoverImageView.setImageDrawable( ContextCompat.getDrawable(this.mAdapterContext, R.drawable.ic_launcher) );
        }
        String bookTitle = cursor.getString(cursor.getColumnIndex(AlexandriaContract.BookEntry.TITLE));
        viewHolder.listBookTitleTextView.setText(bookTitle);

        String bookSubTitle = cursor.getString(cursor.getColumnIndex(AlexandriaContract.BookEntry.SUBTITLE));
        viewHolder.listBookSubTitleTextView.setText(bookSubTitle);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }
    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'book_list_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    class ViewHolder {

        @Bind(R.id.fullBookCoverImageView)
        ImageView fullBookCoverImageView;
        @Bind(R.id.listBookTitleTextView)
        TextView listBookTitleTextView;
        @Bind(R.id.listBookSubTitleTextView)
        TextView listBookSubTitleTextView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
