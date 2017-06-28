package br.com.heiderlopes.metrospapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.heiderlopes.metrospapp.adapter.LinhaAdapter;
import br.com.heiderlopes.metrospapp.adapter.OnItemClickListener;
import br.com.heiderlopes.metrospapp.api.ApiUtils;
import br.com.heiderlopes.metrospapp.api.MetroAPI;
import br.com.heiderlopes.metrospapp.model.Linha;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LinhasActivity extends AppCompatActivity {

    private RecyclerView rvLinhas;
    private LinhaAdapter mAdapter;
    private MetroAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linhas);

        rvLinhas = (RecyclerView) findViewById(R.id.rvLinhas);

        mService = ApiUtils.getMetroAPI();

        mAdapter = new LinhaAdapter(new ArrayList<Linha>(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Linha item) {
                        /*Toast.makeText(getApplicationContext(),
                                item.getCor(), Toast.LENGTH_SHORT).show();*/

                        startActivity(new Intent(getApplicationContext(), MapaActivity.class));
                    }
                });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvLinhas.setLayoutManager(layoutManager);
        rvLinhas.setAdapter(mAdapter);
        rvLinhas.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvLinhas.getContext(),
                LinearLayoutManager.VERTICAL);
        rvLinhas.addItemDecoration(dividerItemDecoration);

        loadLinhas();
    }

    public void loadLinhas() {

        mService.getLinhas()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Linha>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Deu ruim",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Linha> linhas) {
                        mAdapter.update(linhas);
                    }
                });
    }
}
