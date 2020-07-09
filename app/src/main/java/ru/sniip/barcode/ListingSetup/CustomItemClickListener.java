package ru.sniip.barcode.ListingSetup;

import android.view.View;

import ru.sniip.barcode.ContactsListing.ContactsListingAdapter;

/**
 * Created by Jaison.
 */
public interface CustomItemClickListener {
     void onItemClick(View v, int position);
     void onItemClick(View v, int position, ContactsListingAdapter.ActionItem actionItem);
}
