package br.com.heiderlopes.metrospapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.heiderlopes.metrospapp.api.ApiUtils;
import br.com.heiderlopes.metrospapp.api.MetroAPI;
import br.com.heiderlopes.metrospapp.model.Estacao;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private MetroAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        loadLinhas();
    }

    public void loadLinhas() {

        mService = ApiUtils.getMetroAPI();

        mService.getEstacoes("azul")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Estacao>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Deu ruim",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Estacao> estacaos) {
                        colocarNoMapa(estacaos);
                    }
                });
    }

    private void colocarNoMapa(List<Estacao> estacoes) {
        for (Estacao estacao : estacoes) {

            LatLng posicao = new LatLng(
                    estacao.getLatitude(),
                    estacao.getLongitude());

            mMap.addMarker(new MarkerOptions()
                    .position(posicao)
                    .title(estacao.getNome()));
        }
    }
}
