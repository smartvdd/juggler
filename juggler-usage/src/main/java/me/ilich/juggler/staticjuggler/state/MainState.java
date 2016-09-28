package me.ilich.juggler.staticjuggler.state;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.ilich.juggler.staticjuggler.MainFragment;
import me.ilich.juggler.staticjuggler.ToolbarFragment;
import me.ilich.juggler.usage.R;

public class MainState extends AbstractState<VoidParams> {

    public MainState() {
        super(new Grid(R.layout.juggler_layout_content_below_toolbar, Cell.toolbar(), Cell.content()), VoidParams.INSTANCE);
    }

    @Nullable
    @Override
    protected String onTitle(@NotNull Context context, @NotNull VoidParams params) {
        return "Main";
    }

    @Nullable
    @Override
    protected Integer onDisplayOptions() {
        return ActionBar.DISPLAY_SHOW_TITLE;
    }

    @Override
    public Integer onNavigationIcon(@NotNull Context context, @NonNull VoidParams voidParams) {
        return android.R.drawable.ic_menu_info_details;
    }

    @Override
    protected void onNavigationClick(@NotNull Context context, @NotNull VoidParams params) {
        Toast.makeText(context, "sdvvfdf", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    protected Fragment onFragment(@NotNull Cell cell, @NotNull VoidParams params) {
        switch (cell.getType()) {
            case Cell.TYPE_TOOLBAR:
                return ToolbarFragment.create();
            case Cell.TYPE_CONTENT:
                return MainFragment.create();
            default:
                return null;
        }
    }

}
